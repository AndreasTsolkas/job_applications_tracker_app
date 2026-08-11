package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.entity.AppUser;

import java.time.LocalDateTime;

public class AppUserMapper {

    private AppUserMapper() {
    }


    public static AppUserDTO toDTO(AppUser user) {

        if (user == null) {
            return null;
        }

        return AppUserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .enabled(user.getEnabled())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }


    public static AppUser toEntity(AppUserDTO dto) {

        if (dto == null) {
            return null;
        }

        return AppUser.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .userRole(dto.getUserRole())
                .enabled(dto.getEnabled())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }


    public static void updateEntity(AppUser user, AppUserDTO dto) {

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setUserRole(dto.getUserRole());
        user.setEnabled(dto.getEnabled());
        user.setUpdatedAt(LocalDateTime.now());
    }
}