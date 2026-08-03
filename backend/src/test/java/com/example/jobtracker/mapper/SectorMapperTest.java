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