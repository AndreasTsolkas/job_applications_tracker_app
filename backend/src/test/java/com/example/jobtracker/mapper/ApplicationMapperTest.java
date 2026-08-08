package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.ApplicationDTO;
import com.example.jobtracker.entity.*;


class ApplicationMapperTest {


    @Test
    void shouldMapEntityToDTO() {


        AppUser user = AppUser.builder()
                .id(1L)
                .email("user@test.com")
                .build();


        JobPosting jobPosting = JobPosting.builder()
                .id(2L)
                .title("Backend Developer")
                .build();


        ApplicationStatus status = ApplicationStatus.builder()
                .id(3L)
                .name("Applied")
                .build();


        CV cv = CV.builder()
                .id(4L)
                .name("My CV")
                .build();


        CoverLetter coverLetter = CoverLetter.builder()
                .id(5L)
                .name("My Letter")
                .build();


        Recruiter recruiter = Recruiter.builder()
                .id(6L)
                .firstName("John")
                .lastName("Smith")
                .build();



        Application application = Application.builder()
                .id(10L)
                .user(user)
                .jobPosting(jobPosting)
                .status(status)
                .cv(cv)
                .coverLetter(coverLetter)
                .recruiter(recruiter)
                .appliedDate(LocalDate.now())
                .notes("First application")
                .build();



        ApplicationDTO dto =
                ApplicationMapper.toDTO(application);



        assertNotNull(dto);


        assertEquals(
                10L,
                dto.getId()
        );


        assertEquals(
                1L,
                dto.getUserId()
        );


        assertEquals(
                2L,
                dto.getJobPostingId()
        );


        assertEquals(
                3L,
                dto.getStatusId()
        );


        assertEquals(
                4L,
                dto.getCvId()
        );


        assertEquals(
                5L,
                dto.getCoverLetterId()
        );


        assertEquals(
                6L,
                dto.getRecruiterId()
        );


        assertEquals(
                "First application",
                dto.getNotes()
        );
    }



    @Test
    void shouldMapDTOToEntity() {


        ApplicationDTO dto = ApplicationDTO.builder()
                .id(10L)
                .userId(1L)
                .jobPostingId(2L)
                .statusId(3L)
                .cvId(4L)
                .coverLetterId(5L)
                .recruiterId(6L)
                .notes("Test application")
                .build();



        Application application =
                ApplicationMapper.toEntity(dto);



        assertNotNull(application);



        assertEquals(
                10L,
                application.getId()
        );


        assertNotNull(application.getUser());

        assertEquals(
                1L,
                application.getUser().getId()
        );


        assertNotNull(application.getJobPosting());

        assertEquals(
                2L,
                application.getJobPosting().getId()
        );


        assertNotNull(application.getStatus());

        assertEquals(
                3L,
                application.getStatus().getId()
        );


        assertNotNull(application.getCv());

        assertEquals(
                4L,
                application.getCv().getId()
        );


        assertNotNull(application.getCoverLetter());

        assertEquals(
                5L,
                application.getCoverLetter().getId()
        );


        assertNotNull(application.getRecruiter());

        assertEquals(
                6L,
                application.getRecruiter().getId()
        );
    }



    @Test
    void shouldHandleNullRelationsWhenMappingEntityToDTO() {


        Application application = Application.builder()
                .id(1L)
                .notes("No relations")
                .build();



        ApplicationDTO dto =
                ApplicationMapper.toDTO(application);



        assertNotNull(dto);


        assertNull(dto.getUserId());

        assertNull(dto.getJobPostingId());

        assertNull(dto.getStatusId());

        assertNull(dto.getCvId());

        assertNull(dto.getCoverLetterId());

        assertNull(dto.getRecruiterId());
    }



    @Test
    void shouldUpdateEntityFromDTO() {


        Application application = Application.builder()
                .id(10L)
                .notes("Old notes")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();


        LocalDateTime originalCreatedAt = application.getCreatedAt();


        ApplicationDTO dto = ApplicationDTO.builder()
                .userId(1L)
                .jobPostingId(2L)
                .statusId(3L)
                .cvId(4L)
                .coverLetterId(5L)
                .recruiterId(6L)
                .appliedDate(LocalDate.now())
                .notes("Updated notes")
                .build();


        ApplicationMapper.updateEntity(application, dto);


        assertEquals(
                "Updated notes",
                application.getNotes()
        );


        assertNotNull(application.getUser());

        assertEquals(
                1L,
                application.getUser().getId()
        );


        assertNotNull(application.getJobPosting());

        assertEquals(
                2L,
                application.getJobPosting().getId()
        );


        assertNotNull(application.getStatus());

        assertEquals(
                3L,
                application.getStatus().getId()
        );


        assertNotNull(application.getCv());

        assertEquals(
                4L,
                application.getCv().getId()
        );


        assertNotNull(application.getCoverLetter());

        assertEquals(
                5L,
                application.getCoverLetter().getId()
        );


        assertNotNull(application.getRecruiter());

        assertEquals(
                6L,
                application.getRecruiter().getId()
        );


        assertEquals(
                originalCreatedAt,
                application.getCreatedAt()
        );


        assertNotNull(application.getUpdatedAt());
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {


        ApplicationDTO dto =
                ApplicationMapper.toDTO(null);


        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {


        Application application =
                ApplicationMapper.toEntity(null);


        assertNull(application);
    }
}