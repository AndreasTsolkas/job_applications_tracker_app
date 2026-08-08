package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.RecruiterDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.Recruiter;

import java.time.LocalDateTime;

public class RecruiterMapper {

    private RecruiterMapper() {
    }

    public static RecruiterDTO toDTO(Recruiter recruiter) {

        if (recruiter == null) {
            return null;
        }

        return RecruiterDTO.builder()
                .id(recruiter.getId())
                .firstName(recruiter.getFirstName())
                .lastName(recruiter.getLastName())
                .email(recruiter.getEmail())
                .linkedinUrl(recruiter.getLinkedinUrl())
                .companyId(
                        recruiter.getCompany() != null
                                ? recruiter.getCompany().getId()
                                : null
                )
                .createdAt(recruiter.getCreatedAt())
                .updatedAt(recruiter.getUpdatedAt())
                .build();
    }


    public static Recruiter toEntity(RecruiterDTO dto) {

        if (dto == null) {
            return null;
        }

        Recruiter recruiter = Recruiter.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .linkedinUrl(dto.getLinkedinUrl())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();

        if (dto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(dto.getCompanyId());
            recruiter.setCompany(company);
        }

        return recruiter;
    }


    public static void updateEntity(Recruiter recruiter, RecruiterDTO dto) {

        recruiter.setFirstName(dto.getFirstName());
        recruiter.setLastName(dto.getLastName());
        recruiter.setEmail(dto.getEmail());
        recruiter.setLinkedinUrl(dto.getLinkedinUrl());

        if (dto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(dto.getCompanyId());
            recruiter.setCompany(company);
        }

        recruiter.setUpdatedAt(LocalDateTime.now());
    }
}