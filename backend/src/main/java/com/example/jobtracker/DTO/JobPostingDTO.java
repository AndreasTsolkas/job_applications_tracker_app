package com.example.jobtracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostingDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String title;

    private String description;

    @Size(max = 150)
    private String location;

    @NotNull
    private Long companyId;

    @NotNull
    private Long jobRoleId;

    @NotNull
    private Long employmentTypeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}