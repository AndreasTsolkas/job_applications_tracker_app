package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.CoverLetterDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.CoverLetter;

public class CoverLetterMapper {

    private CoverLetterMapper() {
    }

    public static CoverLetterDTO toDTO(CoverLetter coverLetter) {

        if (coverLetter == null) {
            return null;
        }

        return CoverLetterDTO.builder()
                .id(coverLetter.getId())
                .userId(
                        coverLetter.getUser() != null
                                ? coverLetter.getUser().getId()
                                : null
                )
                .name(coverLetter.getName())
                .content(coverLetter.getContent())
                .filePath(coverLetter.getFilePath())
                .createdAt(coverLetter.getCreatedAt())
                .updatedAt(coverLetter.getUpdatedAt())
                .build();
    }


    public static CoverLetter toEntity(CoverLetterDTO dto) {

        if (dto == null) {
            return null;
        }

        CoverLetter coverLetter = CoverLetter.builder()
                .id(dto.getId())
                .name(dto.getName())
                .content(dto.getContent())
                .filePath(dto.getFilePath())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();


        if (dto.getUserId() != null) {
            AppUser user = new AppUser();
            user.setId(dto.getUserId());
            coverLetter.setUser(user);
        }

        return coverLetter;
    }
}