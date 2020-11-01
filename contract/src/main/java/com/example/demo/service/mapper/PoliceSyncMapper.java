package com.example.demo.service.mapper;

import com.example.demo.model.PoliceDTO;
import com.example.demo.service.dto.PoliceSyncDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PoliceSyncMapper {

    PoliceSyncDTO toSyncDTO(PoliceDTO policeDTO);
}
