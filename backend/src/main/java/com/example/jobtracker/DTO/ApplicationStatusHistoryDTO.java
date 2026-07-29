package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusHistoryDTO {

    private Long id;

    private Long applicationId;

    private Long applicationStatusId;

    private String notes;

    private LocalDateTime changedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}