package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.SectorDTO;
import com.example.jobtracker.entity.Sector;

public class SectorMapper {

    private SectorMapper() {
    }

    public static SectorDTO toDTO(Sector sector) {

        if (sector == null) {
            return null;
        }

        return SectorDTO.builder()
                .id(sector.getId())
                .name(sector.getName())
                .description(sector.getDescription())
                .createdAt(sector.getCreatedAt())
                .updatedAt(sector.getUpdatedAt())
                .build();
    }

    public static Sector toEntity(SectorDTO dto) {

        if (dto == null) {
            return null;
        }

        return Sector.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }
}