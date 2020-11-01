package com.example.demo.service.dto;

import java.math.BigDecimal;

public class PoliceSyncDTO {
    private Long id;

    private String name;

    private BigDecimal faceValue;

    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public PoliceSyncDTO(Long id, String name, BigDecimal faceValue) {
        this.id = id;
        this.name = name;
        this.faceValue = faceValue;
    }

    @Override
    public String toString() {
        return "PoliceSyncDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faceValue=" + faceValue +
                ", customerId=" + customerId +
                '}';
    }

    public PoliceSyncDTO() {
    }
}
