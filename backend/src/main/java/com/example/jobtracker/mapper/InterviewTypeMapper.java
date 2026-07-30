package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.InterviewTypeDTO;
import com.example.jobtracker.entity.InterviewType;

public class InterviewTypeMapper {

    private InterviewTypeMapper() {
    }

    public static InterviewTypeDTO toDTO(InterviewType interviewType) {

        if (interviewType == null) {
            return null;
        }

        return InterviewTypeDTO.builder()
                .id(interviewType.getId())
                .name(interviewType.getName())
                .build();
    }


    public static InterviewType toEntity(InterviewTypeDTO dto) {

        if (dto == null) {
            return null;
        }

        return InterviewType.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}