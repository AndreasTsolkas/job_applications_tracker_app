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
public class CompanyDTO {

    private Long id;

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 255)
    private String website;

    @Size(max = 150)
    private String location;

    @NotNull
    private Long sectorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}