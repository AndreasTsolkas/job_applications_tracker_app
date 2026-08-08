package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.InterviewResultDTO;
import com.example.jobtracker.entity.InterviewResult;

public class InterviewResultMapper {

    private InterviewResultMapper() {
    }

    public static InterviewResultDTO toDTO(InterviewResult interviewResult) {

        if (interviewResult == null) {
            return null;
        }

        return InterviewResultDTO.builder()
                .id(interviewResult.getId())
                .name(interviewResult.getName())
                .build();
    }


    public static InterviewResult toEntity(InterviewResultDTO dto) {

        if (dto == null) {
            return null;
        }

        return InterviewResult.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }


    public static void updateEntity(InterviewResult interviewResult, InterviewResultDTO dto) {

        interviewResult.setName(dto.getName());
    }
}