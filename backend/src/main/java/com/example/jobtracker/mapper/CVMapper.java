package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.CVDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.CV;

public class CVMapper {

    private CVMapper() {
    }

    public static CVDTO toDTO(CV cv) {

        if (cv == null) {
            return null;
        }

        return CVDTO.builder()
                .id(cv.getId())
                .userId(
                        cv.getUser() != null
                                ? cv.getUser().getId()
                                : null
                )
                .name(cv.getName())
                .filePath(cv.getFilePath())
                .isActive(cv.getIsActive())
                .createdAt(cv.getCreatedAt())
                .updatedAt(cv.getUpdatedAt())
                .build();
    }


    public static CV toEntity(CVDTO dto) {

        if (dto == null) {
            return null;
        }

        CV cv = CV.builder()
                .id(dto.getId())
                .name(dto.getName())
                .filePath(dto.getFilePath())
                .isActive(dto.getIsActive())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();


        if (dto.getUserId() != null) {
            AppUser user = new AppUser();
            user.setId(dto.getUserId());
            cv.setUser(user);
        }

        return cv;
    }
}