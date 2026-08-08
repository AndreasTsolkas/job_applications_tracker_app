package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.RecruiterDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.Recruiter;


class RecruiterMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        Company company = Company.builder()
                .id(10L)
                .name("Google")
                .build();


        Recruiter recruiter = Recruiter.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@google.com")
                .company(company)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        RecruiterDTO dto = RecruiterMapper.toDTO(recruiter);


        assertNotNull(dto);

        assertEquals(
                1L,
                dto.getId()
        );

        assertEquals(
                "John",
                dto.getFirstName()
        );

        assertEquals(
                "Smith",
                dto.getLastName()
        );

        assertEquals(
                "john@google.com",
                dto.getEmail()
        );

        assertEquals(
                10L,
                dto.getCompanyId()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        RecruiterDTO dto = RecruiterDTO.builder()
                .id(1L)
                .firstName("Maria")
                .lastName("Brown")
                .email("maria@test.com")
                .companyId(20L)
                .build();


        Recruiter recruiter = RecruiterMapper.toEntity(dto);


        assertNotNull(recruiter);

        assertEquals(
                1L,
                recruiter.getId()
        );

        assertEquals(
                "Maria",
                recruiter.getFirstName()
        );

        assertEquals(
                "Brown",
                recruiter.getLastName()
        );

        assertNotNull(
                recruiter.getCompany()
        );

        assertEquals(
                20L,
                recruiter.getCompany().getId()
        );
    }



    @Test
    void shouldHandleNullCompanyWhenMappingEntityToDTO() {

        Recruiter recruiter = Recruiter.builder()
                .id(1L)
                .firstName("Alex")
                .lastName("Green")
                .build();


        RecruiterDTO dto = RecruiterMapper.toDTO(recruiter);


        assertNotNull(dto);

        assertNull(
                dto.getCompanyId()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {

        Recruiter recruiter = Recruiter.builder()
                .id(1L)
                .firstName("Old")
                .lastName("Name")
                .email("old@test.com")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        LocalDateTime originalCreatedAt = recruiter.getCreatedAt();

        RecruiterDTO dto = RecruiterDTO.builder()
                .firstName("New")
                .lastName("Recruiter")
                .email("new@test.com")
                .companyId(30L)
                .build();


        RecruiterMapper.updateEntity(recruiter, dto);


        assertEquals(
                "New",
                recruiter.getFirstName()
        );

        assertEquals(
                "Recruiter",
                recruiter.getLastName()
        );

        assertEquals(
                "new@test.com",
                recruiter.getEmail()
        );

        assertNotNull(
                recruiter.getCompany()
        );

        assertEquals(
                30L,
                recruiter.getCompany().getId()
        );

        assertEquals(
                originalCreatedAt,
                recruiter.getCreatedAt()
        );

        assertNotNull(recruiter.getUpdatedAt());
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        RecruiterDTO dto = RecruiterMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        Recruiter recruiter = RecruiterMapper.toEntity(null);

        assertNull(recruiter);
    }
}