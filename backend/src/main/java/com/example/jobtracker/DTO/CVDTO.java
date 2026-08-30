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
public class CVDTO {

    private Long id;

    private Long userId;

    @NotBlank
    @Size(max = 150)
    private String name;

    @Size(max = 500)
    private String filePath;

    @NotNull
    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}