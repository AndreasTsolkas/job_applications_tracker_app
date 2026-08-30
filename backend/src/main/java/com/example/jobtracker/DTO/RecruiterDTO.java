package com.example.jobtracker.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruiterDTO {

    private Long id;

    @Size(max = 80)
    private String firstName;

    @Size(max = 80)
    private String lastName;

    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 255)
    private String linkedinUrl;

    @NotNull
    private Long companyId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}