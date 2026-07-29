package com.example.jobtracker.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CVDTO {

    private Long id;

    private String fileName;

    private String filePath;

    private String description;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}