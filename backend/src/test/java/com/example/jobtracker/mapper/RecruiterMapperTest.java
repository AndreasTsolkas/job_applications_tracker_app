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