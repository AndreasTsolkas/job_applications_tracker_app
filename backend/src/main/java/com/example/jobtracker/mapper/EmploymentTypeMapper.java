package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.EmploymentTypeDTO;
import com.example.jobtracker.entity.EmploymentType;

public class EmploymentTypeMapper {

    private EmploymentTypeMapper() {
    }

    public static EmploymentTypeDTO toDTO(EmploymentType employmentType) {

        if (employmentType == null) {
            return null;
        }

        return EmploymentTypeDTO.builder()
                .id(employmentType.getId())
                .name(employmentType.getName())
                .build();
    }


    public static EmploymentType toEntity(EmploymentTypeDTO dto) {

        if (dto == null) {
            return null;
        }

        return EmploymentType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}