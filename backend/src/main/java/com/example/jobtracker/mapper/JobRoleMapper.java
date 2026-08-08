package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.JobRoleDTO;
import com.example.jobtracker.entity.JobRole;

import java.time.LocalDateTime;

public class JobRoleMapper {

    private JobRoleMapper() {
    }

    public static JobRoleDTO toDTO(JobRole jobRole) {

        if (jobRole == null) {
            return null;
        }

        return JobRoleDTO.builder()
                .id(jobRole.getId())
                .name(jobRole.getName())
                .description(jobRole.getDescription())
                .createdAt(jobRole.getCreatedAt())
                .updatedAt(jobRole.getUpdatedAt())
                .build();
    }


    public static JobRole toEntity(JobRoleDTO dto) {

        if (dto == null) {
            return null;
        }

        return JobRole.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }


    public static void updateEntity(JobRole jobRole, JobRoleDTO dto) {

        jobRole.setName(dto.getName());
        jobRole.setDescription(dto.getDescription());
        jobRole.setUpdatedAt(LocalDateTime.now());
    }
}