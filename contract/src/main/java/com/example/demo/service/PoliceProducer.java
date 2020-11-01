package com.example.demo.service;

import com.example.demo.service.dto.PoliceSyncDTO;
import com.example.demo.service.mapper.PoliceSyncMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
public class PoliceProducer {

    private final Logger log = LoggerFactory.getLogger(PoliceProducer.class);
    private PoliceSyncMapper policeSyncMapper;


    @Autowired
    public PoliceProducer(PoliceSyncMapper policeSyncMapper) {
        this.policeSyncMapper = policeSyncMapper;

    }

    @SendTo(Source.OUTPUT)
    public Message<PoliceSyncDTO> produce(PoliceSyncDTO policeSyncDTO) {
        log.info("sending data to customer API: " + policeSyncDTO);
        return MessageBuilder.withPayload(policeSyncDTO).build();

    }

}
