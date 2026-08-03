package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.SectorDTO;
import com.example.jobtracker.entity.Sector;
import com.example.jobtracker.repository.SectorRepository;


class SectorServiceTest {


    @Mock
    private SectorRepository sectorRepository;


    private SectorService sectorService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        sectorService =
                new SectorService(sectorRepository);
    }



    @Test
    void shouldReturnAllSectors() {


        Sector sector1 = Sector.builder()
                .id(1L)
                .name("IT")
                .build();


        Sector sector2 = Sector.builder()
                .id(2L)
                .name("Finance")
                .build();



        when(sectorRepository.findAll())
                .thenReturn(List.of(sector1, sector2));



        List<SectorDTO> result =
                sectorService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "IT",
                result.get(0).getName()
        );


        assertEquals(
                "Finance",
                result.get(1).getName()
        );


        verify(sectorRepository)
                .findAll();
    }



    @Test
    void shouldReturnSectorById() {


        Sector sector = Sector.builder()
                .id(1L)
                .name("Technology")
                .build();



        when(sectorRepository.findById(1L))
                .thenReturn(Optional.of(sector));



        SectorDTO result =
                sectorService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Technology",
                result.getName()
        );


        verify(sectorRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenSectorNotFound() {


        when(sectorRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> sectorService.findById(99L)
        );


        verify(sectorRepository)
                .findById(99L);
    }



    @Test
    void shouldSaveSector() {


        SectorDTO dto =
                SectorDTO.builder()
                        .name("Healthcare")
                        .build();



        Sector savedSector =
                Sector.builder()
                        .id(1L)
                        .name("Healthcare")
                        .build();



        when(sectorRepository.save(any(Sector.class)))
                .thenReturn(savedSector);



        SectorDTO result =
                sectorService.save(dto);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Healthcare",
                result.getName()
        );


        verify(sectorRepository)
                .save(any(Sector.class));
    }



    @Test
    void shouldDeleteSector() {


        sectorService.delete(1L);


        verify(sectorRepository)
                .deleteById(1L);
    }
}