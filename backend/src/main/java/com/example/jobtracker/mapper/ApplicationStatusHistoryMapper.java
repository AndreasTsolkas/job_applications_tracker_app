package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.ApplicationStatusHistoryDTO;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.entity.ApplicationStatus;
import com.example.jobtracker.entity.ApplicationStatusHistory;

public class ApplicationStatusHistoryMapper {

    private ApplicationStatusHistoryMapper() {
    }


    public static ApplicationStatusHistoryDTO toDTO(ApplicationStatusHistory history) {

        if (history == null) {
            return null;
        }

        return ApplicationStatusHistoryDTO.builder()
                .id(history.getId())
                .applicationId(
                        history.getApplication() != null
                                ? history.getApplication().getId()
                                : null
                )
                .statusId(
                        history.getStatus() != null
                                ? history.getStatus().getId()
                                : null
                )
                .notes(history.getNotes())
                .changedAt(history.getChangedAt())
                .build();
    }


    public static ApplicationStatusHistory toEntity(ApplicationStatusHistoryDTO dto) {

        if (dto == null) {
            return null;
        }

        ApplicationStatusHistory history = ApplicationStatusHistory.builder()
                .id(dto.getId())
                .notes(dto.getNotes())
                .changedAt(dto.getChangedAt())
                .build();


        if (dto.getApplicationId() != null) {
            Application application = new Application();
            application.setId(dto.getApplicationId());
            history.setApplication(application);
        }


        if (dto.getStatusId() != null) {
            ApplicationStatus status = new ApplicationStatus();
            status.setId(dto.getStatusId());
            history.setStatus(status);
        }


        return history;
    }


    public static void updateEntity(ApplicationStatusHistory history, ApplicationStatusHistoryDTO dto) {

        history.setNotes(dto.getNotes());
        history.setChangedAt(dto.getChangedAt());

        if (dto.getApplicationId() != null) {
            Application application = new Application();
            application.setId(dto.getApplicationId());
            history.setApplication(application);
        }

        if (dto.getStatusId() != null) {
            ApplicationStatus status = new ApplicationStatus();
            status.setId(dto.getStatusId());
            history.setStatus(status);
        }
    }
}