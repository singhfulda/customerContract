package com.example.demo.service.mapper;

import com.example.demo.domain.Police;
import com.example.demo.model.PoliceDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PoliceMapper {

    PoliceDTO toDto(Police police);

    Police toEntity(PoliceDTO policeDTO);
}
