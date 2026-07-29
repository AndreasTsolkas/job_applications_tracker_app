package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {

    private Long id;

    private Long userId;

    private Long jobPostingId;

    private Long applicationStatusId;

    private Long cvId;

    private Long coverLetterId;

    private LocalDate applicationDate;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}