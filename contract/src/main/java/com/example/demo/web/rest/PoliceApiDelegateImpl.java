package com.example.demo.web.rest;

import com.example.demo.api.PoliceApiDelegate;
import com.example.demo.model.PoliceDTO;
import com.example.demo.service.PoliceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.concurrent.ExecutionException;

@Service
public class PoliceApiDelegateImpl implements PoliceApiDelegate {

    private final Logger log = LoggerFactory.getLogger(PoliceApiDelegateImpl.class);
    private final NativeWebRequest request;
    private final PoliceService policeService;


    @Autowired
    public PoliceApiDelegateImpl(NativeWebRequest request, PoliceService policeService) {
        this.request = request;
        this.policeService = policeService;
    }


    @Override
    public ResponseEntity<PoliceDTO> createPolice(PoliceDTO body) {
        log.info("Request to create police: " + body);
        PoliceDTO policeUpdated = null;
        try {
            policeUpdated = policeService.createPolice(body);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(policeUpdated);
    }

    @Override
    public ResponseEntity<Void> deletePolice(Long id) {
        log.info("Request to delete police with id: " + id);
        policeService.deletePolice(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PoliceDTO> getPoliceById(Long id) {
        log.info("Request to get police by id: " + id);
        return ResponseEntity.ok(policeService.getPoliceById(id));
    }

    @Override
    public ResponseEntity<PoliceDTO> updatePolice(Long id, PoliceDTO policeDTO) {
        log.info("Request to update police: " + policeDTO);
        try {
            return ResponseEntity.ok(policeService.updatePolice(policeDTO));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
