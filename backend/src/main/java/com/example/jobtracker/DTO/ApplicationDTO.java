package com.example.jobtracker.DTO;

import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long userId;

    @NotNull
    private Long jobPostingId;

    @NotNull
    private Long statusId;

    private Long cvId;

    private Long coverLetterId;

    private Long recruiterId;

    private LocalDate appliedDate;

    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}