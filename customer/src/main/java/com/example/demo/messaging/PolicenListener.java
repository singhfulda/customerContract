package com.example.demo.messaging;

import com.example.demo.domain.Customer;
import com.example.demo.domain.Police;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PoliceRepository;
import com.example.demo.service.dto.PoliceSyncDTO;
import com.example.demo.service.mapper.PoliceSyncMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableBinding(Sink.class)
public class PolicenListener {
    Logger logger = LoggerFactory.getLogger(PolicenListener.class);

    private PoliceRepository policeRepository;
    private CustomerRepository customerRepository;
    private PoliceSyncMapper policeSyncMapper;

    @Autowired
    public PolicenListener(PoliceRepository policeRepository, CustomerRepository customerRepository, PoliceSyncMapper policeSyncMapper) {
        this.policeRepository = policeRepository;
        this.customerRepository = customerRepository;
        this.policeSyncMapper = policeSyncMapper;
    }

    @StreamListener(Sink.INPUT)
    public void listenPolicen(@Payload PoliceSyncDTO policeSyncDTO) {
        logger.info("recieved request to sync: " + policeSyncDTO);
        if (policeSyncDTO == null || policeSyncDTO.getId().equals(null) || policeSyncDTO.getCustomerId().equals(null)) {
            logger.error("PoliceSyncDTO discarded: " + policeSyncDTO);
        } else {
            Optional<Customer> optionalCustomer = customerRepository.findById(policeSyncDTO.getCustomerId());
            if (optionalCustomer.isPresent()) {
                Police police = policeSyncMapper.toEntity(policeSyncDTO);
                policeRepository.save(police);
            }
        }
    }
}
