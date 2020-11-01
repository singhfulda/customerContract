package com.example.demo.service.mapper;

import com.example.demo.domain.Police;
import com.example.demo.service.dto.PoliceSyncDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface PoliceSyncMapper {

    @Mapping(source = "customerId", target = "customer.id")
    Police toEntity(PoliceSyncDTO policeSyncDTO);

}