package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {

    private Long id;

    private String name;

    private String website;

    private String location;

    private Long sectorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}