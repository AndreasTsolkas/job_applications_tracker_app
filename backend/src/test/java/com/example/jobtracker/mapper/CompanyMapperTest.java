package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.CompanyDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.Sector;


class CompanyMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        Sector sector = Sector.builder()
                .id(10L)
                .name("Technology")
                .build();


        Company company = Company.builder()
                .id(1L)
                .name("Google")
                .website("https://google.com")
                .location("USA")
                .sector(sector)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        CompanyDTO dto = CompanyMapper.toDTO(company);


        assertNotNull(dto);

        assertEquals(
                1L,
                dto.getId()
        );

        assertEquals(
                "Google",
                dto.getName()
        );

        assertEquals(
                "https://google.com",
                dto.getWebsite()
        );

        assertEquals(
                "USA",
                dto.getLocation()
        );

        assertEquals(
                10L,
                dto.getSectorId()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        CompanyDTO dto = CompanyDTO.builder()
                .id(1L)
                .name("Microsoft")
                .website("https://microsoft.com")
                .location("USA")
                .sectorId(20L)
                .build();


        Company company = CompanyMapper.toEntity(dto);


        assertNotNull(company);

        assertEquals(
                1L,
                company.getId()
        );

        assertEquals(
                "Microsoft",
                company.getName()
        );


        assertNotNull(
                company.getSector()
        );


        assertEquals(
                20L,
                company.getSector().getId()
        );
    }



    @Test
    void shouldHandleNullSectorWhenMappingEntityToDTO() {

        Company company = Company.builder()
                .id(1L)
                .name("Netflix")
                .build();


        CompanyDTO dto = CompanyMapper.toDTO(company);


        assertNotNull(dto);

        assertNull(
                dto.getSectorId()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        CompanyDTO dto = CompanyMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        Company company = CompanyMapper.toEntity(null);

        assertNull(company);
    }
}