package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.ApplicationStatusDTO;
import com.example.jobtracker.entity.ApplicationStatus;

public class ApplicationStatusMapper {

    private ApplicationStatusMapper() {
    }

    public static ApplicationStatusDTO toDTO(ApplicationStatus applicationStatus) {

        if (applicationStatus == null) {
            return null;
        }

        return ApplicationStatusDTO.builder()
                .id(applicationStatus.getId())
                .name(applicationStatus.getName())
                .build();
    }


    public static ApplicationStatus toEntity(ApplicationStatusDTO dto) {

        if (dto == null) {
            return null;
        }

        return ApplicationStatus.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }


    public static void updateEntity(ApplicationStatus applicationStatus, ApplicationStatusDTO dto) {

        applicationStatus.setName(dto.getName());
    }
}