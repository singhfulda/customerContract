package com.example.demo.service;

import com.example.demo.PoliceRepository.PoliceRepository;
import com.example.demo.domain.Police;
import com.example.demo.model.PoliceDTO;
import com.example.demo.service.mapper.PoliceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoliceService {

    private PoliceRepository policeRepository;

    private PoliceMapper policeMapper;

    @Autowired
    public PoliceService(PoliceRepository policeRepository, PoliceMapper policeMapper) {
        this.policeRepository = policeRepository;
        this.policeMapper = policeMapper;
    }

    public PoliceDTO getPoliceById(Long id) {
        Optional<Police> optionalPolice = policeRepository.findById(id);
        if (optionalPolice.isPresent()) {
            return policeMapper.toDto(optionalPolice.get());
        } else {
            throw new PoliceNotFoundException();
        }
    }

    public PoliceDTO createPolice(PoliceDTO policeDTO) {
        Police police = policeRepository.save(policeMapper.toEntity(policeDTO));
        return policeMapper.toDto(police);
    }

    public PoliceDTO updatePolice(PoliceDTO policeDTO) {
        if (policeDTO.getId() == null) {
            throw new PoliceNotFoundException();
        }
        Optional<Police> police = policeRepository.findById(policeDTO.getId());
        if (police.isPresent()) {
            Police policeUpdated = policeRepository.save(policeMapper.toEntity(policeDTO));
            return policeMapper.toDto(policeUpdated);
        } else {
            throw new PoliceNotFoundException();
        }
    }

    public void deletePolice(Long id) {
        policeRepository.deleteById(id);
    }

}
