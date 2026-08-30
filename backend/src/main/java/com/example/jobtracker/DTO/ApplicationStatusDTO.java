package com.example.jobtracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationStatusDTO {

    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;
}