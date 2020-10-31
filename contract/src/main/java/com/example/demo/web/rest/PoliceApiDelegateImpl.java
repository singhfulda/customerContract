package com.example.demo.web.rest;

import com.example.demo.api.PoliceApiDelegate;
import com.example.demo.model.PoliceDTO;
import com.example.demo.service.PoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PoliceApiDelegateImpl implements PoliceApiDelegate {

    @Autowired
    PoliceService policeService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Void> createPolice(PoliceDTO body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePolice(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<PoliceDTO> getPoliceById(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<PoliceDTO> updatePolice(BigDecimal id, PoliceDTO body) {
        return null;
    }
}
