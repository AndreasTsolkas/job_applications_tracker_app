package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.SectorDTO;
import com.example.jobtracker.entity.Sector;


class SectorMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        Sector sector = Sector.builder()
                .id(1L)
                .name("Technology")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        SectorDTO dto = SectorMapper.toDTO(sector);


        assertNotNull(dto);

        assertEquals(
                1L,
                dto.getId()
        );

        assertEquals(
                "Technology",
                dto.getName()
        );

        assertNotNull(dto.getCreatedAt());

        assertNotNull(dto.getUpdatedAt());
    }



    @Test
    void shouldMapDTOToEntity() {

        SectorDTO dto = SectorDTO.builder()
                .id(1L)
                .name("Finance")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        Sector sector = SectorMapper.toEntity(dto);


        assertNotNull(sector);

        assertEquals(
                1L,
                sector.getId()
        );

        assertEquals(
                "Finance",
                sector.getName()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {

        Sector sector = Sector.builder()
                .id(1L)
                .name("Old Name")
                .description("Old description")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        LocalDateTime originalCreatedAt = sector.getCreatedAt();

        SectorDTO dto = SectorDTO.builder()
                .name("New Name")
                .description("New description")
                .build();


        SectorMapper.updateEntity(sector, dto);


        assertEquals(
                "New Name",
                sector.getName()
        );

        assertEquals(
                "New description",
                sector.getDescription()
        );

        assertEquals(
                originalCreatedAt,
                sector.getCreatedAt()
        );

        assertNotNull(sector.getUpdatedAt());
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        SectorDTO dto = SectorMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        Sector sector = SectorMapper.toEntity(null);

        assertNull(sector);
    }
}