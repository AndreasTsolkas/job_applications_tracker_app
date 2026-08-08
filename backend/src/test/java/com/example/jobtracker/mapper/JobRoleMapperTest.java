package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.JobRoleDTO;
import com.example.jobtracker.entity.JobRole;


class JobRoleMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        JobRole jobRole = JobRole.builder()
                .id(1L)
                .name("Backend Developer")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        JobRoleDTO dto = JobRoleMapper.toDTO(jobRole);


        assertNotNull(dto);

        assertEquals(
                1L,
                dto.getId()
        );

        assertEquals(
                "Backend Developer",
                dto.getName()
        );

        assertNotNull(
                dto.getCreatedAt()
        );

        assertNotNull(
                dto.getUpdatedAt()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        JobRoleDTO dto = JobRoleDTO.builder()
                .id(1L)
                .name("Frontend Developer")
                .build();


        JobRole jobRole = JobRoleMapper.toEntity(dto);


        assertNotNull(jobRole);

        assertEquals(
                1L,
                jobRole.getId()
        );

        assertEquals(
                "Frontend Developer",
                jobRole.getName()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {

        JobRole jobRole = JobRole.builder()
                .id(1L)
                .name("Old Role")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        LocalDateTime originalCreatedAt = jobRole.getCreatedAt();

        JobRoleDTO dto = JobRoleDTO.builder()
                .name("New Role")
                .description("Updated description")
                .build();


        JobRoleMapper.updateEntity(jobRole, dto);


        assertEquals(
                "New Role",
                jobRole.getName()
        );

        assertEquals(
                "Updated description",
                jobRole.getDescription()
        );

        assertEquals(
                originalCreatedAt,
                jobRole.getCreatedAt()
        );

        assertNotNull(jobRole.getUpdatedAt());
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        JobRoleDTO dto = JobRoleMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        JobRole jobRole = JobRoleMapper.toEntity(null);

        assertNull(jobRole);
    }
}