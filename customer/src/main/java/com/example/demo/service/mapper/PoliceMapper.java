package com.example.demo.service.mapper;

import com.example.demo.domain.Police;
import com.example.demo.service.dto.PoliceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PoliceMapper {


    Police toEntity(PoliceDTO policeDTO);

    PoliceDTO toDto(Police police);

    List<Police> toEntities(List<PoliceDTO> policeDTOs);

    List<PoliceDTO> toDtos(List<Police> polices);
}