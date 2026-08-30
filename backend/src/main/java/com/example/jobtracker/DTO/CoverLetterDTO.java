package com.example.jobtracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoverLetterDTO {

    private Long id;

    private Long userId;

    @NotBlank
    @Size(max = 150)
    private String name;

    private String content;

    @Size(max = 500)
    private String filePath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}