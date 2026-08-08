package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.EmploymentTypeDTO;
import com.example.jobtracker.entity.EmploymentType;


class EmploymentTypeMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        EmploymentType employmentType = EmploymentType.builder()
                .id(1L)
                .name("Full Time")
                .build();


        EmploymentTypeDTO dto =
                EmploymentTypeMapper.toDTO(employmentType);


        assertNotNull(dto);

        assertEquals(
                1L,
                dto.getId()
        );

        assertEquals(
                "Full Time",
                dto.getName()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        EmploymentTypeDTO dto = EmploymentTypeDTO.builder()
                .id(1L)
                .name("Part Time")
                .build();


        EmploymentType employmentType =
                EmploymentTypeMapper.toEntity(dto);


        assertNotNull(employmentType);

        assertEquals(
                1L,
                employmentType.getId()
        );

        assertEquals(
                "Part Time",
                employmentType.getName()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {

        EmploymentType employmentType = EmploymentType.builder()
                .id(1L)
                .name("Full Time")
                .build();


        EmploymentTypeDTO dto = EmploymentTypeDTO.builder()
                .name("Contract")
                .build();


        EmploymentTypeMapper.updateEntity(employmentType, dto);


        assertEquals(
                "Contract",
                employmentType.getName()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        EmploymentTypeDTO dto =
                EmploymentTypeMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        EmploymentType employmentType =
                EmploymentTypeMapper.toEntity(null);

        assertNull(employmentType);
    }
}