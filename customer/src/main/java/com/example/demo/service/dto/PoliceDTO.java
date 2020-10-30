package com.example.demo.service.dto;

import java.math.BigDecimal;

public class PoliceDTO {
    private Long id;

    private String name;

    private BigDecimal faceValue;

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

    public PoliceDTO(Long id, String name, BigDecimal faceValue) {
        this.id = id;
        this.name = name;
        this.faceValue = faceValue;
    }

    @Override
    public String toString() {
        return "PoliceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faceValue=" + faceValue +
                '}';
    }

    public PoliceDTO() {
    }
}
