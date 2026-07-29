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

    private String title;

    private String content;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}