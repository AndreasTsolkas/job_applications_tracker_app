package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.JobPostingDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.EmploymentType;
import com.example.jobtracker.entity.JobPosting;
import com.example.jobtracker.entity.JobRole;

public class JobPostingMapper {

    private JobPostingMapper() {
    }

    public static JobPostingDTO toDTO(JobPosting jobPosting) {

        if (jobPosting == null) {
            return null;
        }

        return JobPostingDTO.builder()
                .id(jobPosting.getId())
                .title(jobPosting.getTitle())
                .description(jobPosting.getDescription())
                .location(jobPosting.getLocation())
                .companyId(
                        jobPosting.getCompany() != null
                                ? jobPosting.getCompany().getId()
                                : null
                )
                .jobRoleId(
                        jobPosting.getJobRole() != null
                                ? jobPosting.getJobRole().getId()
                                : null
                )
                .employmentTypeId(
                        jobPosting.getEmploymentType() != null
                                ? jobPosting.getEmploymentType().getId()
                                : null
                )
                .createdAt(jobPosting.getCreatedAt())
                .updatedAt(jobPosting.getUpdatedAt())
                .build();
    }


    public static JobPosting toEntity(JobPostingDTO dto) {

        if (dto == null) {
            return null;
        }

        JobPosting jobPosting = JobPosting.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();


        if (dto.getCompanyId() != null) {
            Company company = new Company();
            company.setId(dto.getCompanyId());
            jobPosting.setCompany(company);
        }


        if (dto.getJobRoleId() != null) {
            JobRole jobRole = new JobRole();
            jobRole.setId(dto.getJobRoleId());
            jobPosting.setJobRole(jobRole);
        }


        if (dto.getEmploymentTypeId() != null) {
            EmploymentType employmentType = new EmploymentType();
            employmentType.setId(dto.getEmploymentTypeId());
            jobPosting.setEmploymentType(employmentType);
        }


        return jobPosting;
    }
}