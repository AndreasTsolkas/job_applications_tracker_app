package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {

    private Long id;

    private Long userId;

    private Long jobPostingId;

    private Long statusId;

    private Long cvId;

    private Long coverLetterId;

    private Long recruiterId;

    private LocalDate appliedDate;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}