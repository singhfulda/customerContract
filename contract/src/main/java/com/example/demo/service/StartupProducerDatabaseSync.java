package com.example.demo.service;

import com.example.demo.service.mapper.PoliceSyncMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class StartupProducerDatabaseSync {

    private PoliceService policeService;
    private PoliceProducer producer;
    private PoliceSyncMapper policeSyncMapper;

    @Autowired
    public StartupProducerDatabaseSync(PoliceService policeService, PoliceProducer producer, PoliceSyncMapper policeSyncMapper) {
        this.policeService = policeService;
        this.producer = producer;
        this.policeSyncMapper = policeSyncMapper;
    }

    @EventListener
    public void onApplicationStartupSyncDatabaseToTopic(ContextRefreshedEvent event) {
        policeService.getAllPoliceList().forEach(policeDTO -> {
            producer.produce(policeSyncMapper.toSyncDTO(policeDTO));

        });
    }
}
