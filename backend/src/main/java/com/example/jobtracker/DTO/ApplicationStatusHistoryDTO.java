package com.example.jobtracker.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusHistoryDTO {

    private Long id;

    @NotNull
    private Long applicationId;

    @NotNull
    private Long statusId;

    private String notes;

    private LocalDateTime changedAt;
}