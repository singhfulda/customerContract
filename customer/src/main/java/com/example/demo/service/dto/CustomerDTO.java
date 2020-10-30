package com.example.demo.service.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CustomerDTO implements Serializable {
    private Long id;
    private String name;
    private List<PoliceDTO> polices;

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

    public List<PoliceDTO> getPolices() {
        return polices;
    }

    public void setPolices(List<PoliceDTO> polices) {
        this.polices = polices;
    }

    public void addPolice(PoliceDTO police) {
        this.polices.add(police);
    }

    public CustomerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerDTO(Long id, String name, List<PoliceDTO> polices) {
        this.id = id;
        this.name = name;
        this.polices = polices;
    }

    public CustomerDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return id.equals(that.id) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", polices=" + polices +
                '}';
    }
}
