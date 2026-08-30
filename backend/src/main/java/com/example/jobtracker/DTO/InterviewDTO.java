package com.example.jobtracker.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewDTO {

    private Long id;

    @NotNull
    private Long applicationId;

    @NotNull
    private Long typeId;

    private Long resultId;

    @NotNull
    private LocalDateTime scheduledAt;

    private String notes;

    private LocalDateTime createdAt;
}