package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.JobPostingDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.EmploymentType;
import com.example.jobtracker.entity.JobPosting;
import com.example.jobtracker.entity.JobRole;


class JobPostingMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        Company company = Company.builder()
                .id(10L)
                .name("Google")
                .build();


        JobRole jobRole = JobRole.builder()
                .id(20L)
                .name("Backend Developer")
                .build();


        EmploymentType employmentType = EmploymentType.builder()
                .id(30L)
                .name("Full Time")
                .build();


        JobPosting jobPosting = JobPosting.builder()
                .id(1L)
                .title("Java Developer")
                .description("Spring Boot position")
                .location("Athens")
                .company(company)
                .jobRole(jobRole)
                .employmentType(employmentType)
                .build();


        JobPostingDTO dto =
                JobPostingMapper.toDTO(jobPosting);


        assertNotNull(dto);

        assertEquals(
                1L,
                dto.getId()
        );

        assertEquals(
                "Java Developer",
                dto.getTitle()
        );

        assertEquals(
                "Spring Boot position",
                dto.getDescription()
        );

        assertEquals(
                "Athens",
                dto.getLocation()
        );


        assertEquals(
                10L,
                dto.getCompanyId()
        );


        assertEquals(
                20L,
                dto.getJobRoleId()
        );


        assertEquals(
                30L,
                dto.getEmploymentTypeId()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        JobPostingDTO dto = JobPostingDTO.builder()
                .id(1L)
                .title("Frontend Developer")
                .description("React position")
                .location("Remote")
                .companyId(10L)
                .jobRoleId(20L)
                .employmentTypeId(30L)
                .build();


        JobPosting jobPosting =
                JobPostingMapper.toEntity(dto);


        assertNotNull(jobPosting);


        assertEquals(
                1L,
                jobPosting.getId()
        );


        assertEquals(
                "Frontend Developer",
                jobPosting.getTitle()
        );


        assertNotNull(
                jobPosting.getCompany()
        );

        assertEquals(
                10L,
                jobPosting.getCompany().getId()
        );


        assertNotNull(
                jobPosting.getJobRole()
        );

        assertEquals(
                20L,
                jobPosting.getJobRole().getId()
        );


        assertNotNull(
                jobPosting.getEmploymentType()
        );

        assertEquals(
                30L,
                jobPosting.getEmploymentType().getId()
        );
    }



    @Test
    void shouldHandleNullRelationsWhenMappingEntityToDTO() {

        JobPosting jobPosting = JobPosting.builder()
                .id(1L)
                .title("Developer")
                .build();


        JobPostingDTO dto =
                JobPostingMapper.toDTO(jobPosting);


        assertNotNull(dto);

        assertNull(dto.getCompanyId());

        assertNull(dto.getJobRoleId());

        assertNull(dto.getEmploymentTypeId());
    }



    @Test
    void shouldUpdateEntityFromDTO() {

        JobPosting jobPosting = JobPosting.builder()
                .id(1L)
                .title("Old Title")
                .description("Old description")
                .location("Athens")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        LocalDateTime originalCreatedAt = jobPosting.getCreatedAt();

        JobPostingDTO dto = JobPostingDTO.builder()
                .title("New Title")
                .description("New description")
                .location("Remote")
                .companyId(10L)
                .jobRoleId(20L)
                .employmentTypeId(30L)
                .build();


        JobPostingMapper.updateEntity(jobPosting, dto);


        assertEquals(
                "New Title",
                jobPosting.getTitle()
        );

        assertEquals(
                "New description",
                jobPosting.getDescription()
        );

        assertEquals(
                "Remote",
                jobPosting.getLocation()
        );

        assertNotNull(
                jobPosting.getCompany()
        );

        assertEquals(
                10L,
                jobPosting.getCompany().getId()
        );

        assertNotNull(
                jobPosting.getJobRole()
        );

        assertEquals(
                20L,
                jobPosting.getJobRole().getId()
        );

        assertNotNull(
                jobPosting.getEmploymentType()
        );

        assertEquals(
                30L,
                jobPosting.getEmploymentType().getId()
        );

        assertEquals(
                originalCreatedAt,
                jobPosting.getCreatedAt()
        );

        assertNotNull(jobPosting.getUpdatedAt());
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        JobPostingDTO dto =
                JobPostingMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        JobPosting jobPosting =
                JobPostingMapper.toEntity(null);

        assertNull(jobPosting);
    }
}