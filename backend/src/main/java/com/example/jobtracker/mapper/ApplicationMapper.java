package com.example.jobtracker.mapper;

import com.example.jobtracker.DTO.ApplicationDTO;
import com.example.jobtracker.entity.*;

import java.time.LocalDateTime;

public class ApplicationMapper {

    private ApplicationMapper() {
    }


    public static ApplicationDTO toDTO(Application application) {

        if (application == null) {
            return null;
        }

        return ApplicationDTO.builder()
                .id(application.getId())
                .userId(
                        application.getUser() != null
                                ? application.getUser().getId()
                                : null
                )
                .jobPostingId(
                        application.getJobPosting() != null
                                ? application.getJobPosting().getId()
                                : null
                )
                .statusId(
                        application.getStatus() != null
                                ? application.getStatus().getId()
                                : null
                )
                .cvId(
                        application.getCv() != null
                                ? application.getCv().getId()
                                : null
                )
                .coverLetterId(
                        application.getCoverLetter() != null
                                ? application.getCoverLetter().getId()
                                : null
                )
                .recruiterId(
                        application.getRecruiter() != null
                                ? application.getRecruiter().getId()
                                : null
                )
                .appliedDate(application.getAppliedDate())
                .notes(application.getNotes())
                .createdAt(application.getCreatedAt())
                .updatedAt(application.getUpdatedAt())
                .build();
    }


    public static Application toEntity(ApplicationDTO dto) {

        if (dto == null) {
            return null;
        }

        Application application = Application.builder()
                .id(dto.getId())
                .appliedDate(dto.getAppliedDate())
                .notes(dto.getNotes())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();


        if (dto.getUserId() != null) {
            AppUser user = new AppUser();
            user.setId(dto.getUserId());
            application.setUser(user);
        }


        if (dto.getJobPostingId() != null) {
            JobPosting jobPosting = new JobPosting();
            jobPosting.setId(dto.getJobPostingId());
            application.setJobPosting(jobPosting);
        }


        if (dto.getStatusId() != null) {
            ApplicationStatus status = new ApplicationStatus();
            status.setId(dto.getStatusId());
            application.setStatus(status);
        }


        if (dto.getCvId() != null) {
            CV cv = new CV();
            cv.setId(dto.getCvId());
            application.setCv(cv);
        }


        if (dto.getCoverLetterId() != null) {
            CoverLetter coverLetter = new CoverLetter();
            coverLetter.setId(dto.getCoverLetterId());
            application.setCoverLetter(coverLetter);
        }


        if (dto.getRecruiterId() != null) {
            Recruiter recruiter = new Recruiter();
            recruiter.setId(dto.getRecruiterId());
            application.setRecruiter(recruiter);
        }


        return application;
    }


    public static void updateEntity(Application application, ApplicationDTO dto) {

        application.setAppliedDate(dto.getAppliedDate());
        application.setNotes(dto.getNotes());

        if (dto.getUserId() != null) {
            AppUser user = new AppUser();
            user.setId(dto.getUserId());
            application.setUser(user);
        }

        if (dto.getJobPostingId() != null) {
            JobPosting jobPosting = new JobPosting();
            jobPosting.setId(dto.getJobPostingId());
            application.setJobPosting(jobPosting);
        }

        if (dto.getStatusId() != null) {
            ApplicationStatus status = new ApplicationStatus();
            status.setId(dto.getStatusId());
            application.setStatus(status);
        }

        if (dto.getCvId() != null) {
            CV cv = new CV();
            cv.setId(dto.getCvId());
            application.setCv(cv);
        }

        if (dto.getCoverLetterId() != null) {
            CoverLetter coverLetter = new CoverLetter();
            coverLetter.setId(dto.getCoverLetterId());
            application.setCoverLetter(coverLetter);
        }

        if (dto.getRecruiterId() != null) {
            Recruiter recruiter = new Recruiter();
            recruiter.setId(dto.getRecruiterId());
            application.setRecruiter(recruiter);
        }

        application.setUpdatedAt(LocalDateTime.now());
    }
}
