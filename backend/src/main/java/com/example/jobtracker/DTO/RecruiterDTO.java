package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruiterDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String linkedinUrl;

    private Long companyId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}