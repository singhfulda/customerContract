package com.example.demo.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Police {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private BigDecimal faceValue;

    @Column
    private Long customerId;


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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Police{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faceValue=" + faceValue +
                ", customerId=" + customerId +
                '}';
    }
}
