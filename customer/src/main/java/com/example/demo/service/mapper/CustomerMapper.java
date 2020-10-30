package com.example.demo.service.mapper;

import com.example.demo.domain.Customer;
import com.example.demo.service.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PoliceMapper.class)
public interface CustomerMapper {

    Customer toEntity(CustomerDTO customerDTO);

    CustomerDTO toDto(Customer customer);

}
