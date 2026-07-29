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

    private Long interviewTypeId;

    private Long interviewResultId;

    private LocalDateTime interviewDate;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}