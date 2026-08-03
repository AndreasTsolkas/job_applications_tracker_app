package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.ApplicationStatusDTO;
import com.example.jobtracker.entity.ApplicationStatus;


class ApplicationStatusMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        ApplicationStatus status = ApplicationStatus.builder()
                .id(1L)
                .name("Applied")
                .build();


        ApplicationStatusDTO dto =
                ApplicationStatusMapper.toDTO(status);


        assertNotNull(dto);


        assertEquals(
                1L,
                dto.getId()
        );


        assertEquals(
                "Applied",
                dto.getName()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        ApplicationStatusDTO dto =
                ApplicationStatusDTO.builder()
                        .id(1L)
                        .name("Interview")
                        .build();


        ApplicationStatus status =
                ApplicationStatusMapper.toEntity(dto);


        assertNotNull(status);


        assertEquals(
                1L,
                status.getId()
        );


        assertEquals(
                "Interview",
                status.getName()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        ApplicationStatusDTO dto =
                ApplicationStatusMapper.toDTO(null);


        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        ApplicationStatus status =
                ApplicationStatusMapper.toEntity(null);


        assertNull(status);
    }
}