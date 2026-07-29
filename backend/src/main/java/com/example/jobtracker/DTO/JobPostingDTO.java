package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingDTO {

    private Long id;

    private String title;

    private String description;

    private String requirements;

    private String location;

    private String jobUrl;

    private LocalDate postingDate;

    private Long companyId;

    private Long jobRoleId;

    private Long employmentTypeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}