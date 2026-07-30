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

    private Long statusId;

    private String notes;

    private LocalDateTime changedAt;
}