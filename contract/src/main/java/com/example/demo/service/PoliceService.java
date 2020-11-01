package com.example.demo.service;

import com.example.demo.PoliceRepository.PoliceRepository;
import com.example.demo.domain.Police;
import com.example.demo.model.PoliceDTO;
import com.example.demo.service.dto.PoliceSyncDTO;
import com.example.demo.service.mapper.PoliceMapper;
import com.example.demo.service.mapper.PoliceSyncMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class PoliceService {

    private PoliceRepository policeRepository;

    private PoliceMapper policeMapper;
    private PoliceProducer producer;
    private PoliceSyncMapper policeSyncMapper;

    @Autowired
    public PoliceService(PoliceRepository policeRepository, PoliceMapper policeMapper, PoliceProducer producer, PoliceSyncMapper policeSyncMapper) {
        this.policeRepository = policeRepository;
        this.policeMapper = policeMapper;
        this.producer = producer;
        this.policeSyncMapper = policeSyncMapper;
    }

    public PoliceDTO getPoliceById(Long id) {
        Optional<Police> optionalPolice = policeRepository.findById(id);
        if (optionalPolice.isPresent()) {
            return policeMapper.toDto(optionalPolice.get());
        } else {
            throw new PoliceNotFoundException();
        }
    }

    public PoliceDTO createPolice(PoliceDTO policeDTO) throws ExecutionException, InterruptedException {
        Police police = policeRepository.save(policeMapper.toEntity(policeDTO));
        PoliceDTO dto = policeMapper.toDto(police);
        PoliceSyncDTO policeSyncDTO = policeSyncMapper.toSyncDTO(dto);
        producer.produce(policeSyncDTO);
        return dto;
    }

    public PoliceDTO updatePolice(PoliceDTO policeDTO) throws ExecutionException, InterruptedException {
        if (policeDTO.getId() == null) {
            throw new PoliceNotFoundException();
        }
        Optional<Police> police = policeRepository.findById(policeDTO.getId());
        if (police.isPresent()) {
            Police policeUpdated = policeRepository.save(policeMapper.toEntity(policeDTO));
            PoliceDTO dto = policeMapper.toDto(policeUpdated);
            PoliceSyncDTO policeSyncDTO = policeSyncMapper.toSyncDTO(dto);
            producer.produce(policeSyncDTO);
            return dto;
        } else {
            throw new PoliceNotFoundException();
        }
    }

    public void deletePolice(Long id) {
        policeRepository.deleteById(id);
    }

    public List<PoliceDTO> getAllPoliceList() {
        return policeMapper.toDtos(policeRepository.findAll());
    }

}
