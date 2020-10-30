package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "police")
public class Police implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal faceValue;

    @ManyToOne
    private Customer customer;

    public Police() {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
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

    public Police(Long id, String name, BigDecimal faceValue) {
        this.id = id;
        this.name = name;
        this.faceValue = faceValue;
    }

    @Override
    public String toString() {
        return "Police{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", faceValue='" + faceValue + '\'' +
                '}';
    }
}
