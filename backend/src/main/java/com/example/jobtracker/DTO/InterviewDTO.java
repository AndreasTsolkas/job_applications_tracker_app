package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewDTO {

    private Long id;

    private Long applicationId;

    private Long typeId;

    private Long resultId;

    private LocalDateTime scheduledAt;

    private String notes;

    private LocalDateTime createdAt;
}