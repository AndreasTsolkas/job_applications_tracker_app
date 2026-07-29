package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}