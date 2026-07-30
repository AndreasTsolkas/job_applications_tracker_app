package com.example.jobtracker.DTO;

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

    private String name;

    private String content;

    private String filePath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}