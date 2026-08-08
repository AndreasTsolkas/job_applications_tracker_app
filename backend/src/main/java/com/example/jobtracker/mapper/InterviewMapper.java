package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.InterviewDTO;
import com.example.jobtracker.entity.*;

public class InterviewMapper {

    private InterviewMapper() {
    }


    public static InterviewDTO toDTO(Interview interview) {

        if (interview == null) {
            return null;
        }

        return InterviewDTO.builder()
                .id(interview.getId())
                .applicationId(
                        interview.getApplication() != null
                                ? interview.getApplication().getId()
                                : null
                )
                .typeId(
                        interview.getType() != null
                                ? interview.getType().getId()
                                : null
                )
                .resultId(
                        interview.getResult() != null
                                ? interview.getResult().getId()
                                : null
                )
                .scheduledAt(interview.getScheduledAt())
                .notes(interview.getNotes())
                .createdAt(interview.getCreatedAt())
                .build();
    }


    public static Interview toEntity(InterviewDTO dto) {

        if (dto == null) {
            return null;
        }

        Interview interview = Interview.builder()
                .id(dto.getId())
                .scheduledAt(dto.getScheduledAt())
                .notes(dto.getNotes())
                .createdAt(dto.getCreatedAt())
                .build();


        if (dto.getApplicationId() != null) {
            Application application = new Application();
            application.setId(dto.getApplicationId());
            interview.setApplication(application);
        }


        if (dto.getTypeId() != null) {
            InterviewType type = new InterviewType();
            type.setId(dto.getTypeId());
            interview.setType(type);
        }


        if (dto.getResultId() != null) {
            InterviewResult result = new InterviewResult();
            result.setId(dto.getResultId());
            interview.setResult(result);
        }


        return interview;
    }


    public static void updateEntity(Interview interview, InterviewDTO dto) {

        interview.setScheduledAt(dto.getScheduledAt());
        interview.setNotes(dto.getNotes());

        if (dto.getApplicationId() != null) {
            Application application = new Application();
            application.setId(dto.getApplicationId());
            interview.setApplication(application);
        }

        if (dto.getTypeId() != null) {
            InterviewType type = new InterviewType();
            type.setId(dto.getTypeId());
            interview.setType(type);
        }

        if (dto.getResultId() != null) {
            InterviewResult result = new InterviewResult();
            result.setId(dto.getResultId());
            interview.setResult(result);
        }
    }
}