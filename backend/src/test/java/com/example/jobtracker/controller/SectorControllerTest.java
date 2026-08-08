package com.example.jobtracker.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import com.example.jobtracker.DTO.SectorDTO;
import com.example.jobtracker.service.SectorService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(SectorController.class)
class SectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SectorService sectorService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllSectors() throws Exception {

        SectorDTO sector = SectorDTO.builder()
                .id(1L)
                .name("Technology")
                .build();

        when(sectorService.findAll())
                .thenReturn(List.of(sector));

        mockMvc.perform(get("/api/sectors"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()").value(1))

                .andExpect(jsonPath("$[0].id").value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Technology"));
    }

    @Test
    void shouldReturnSectorById() throws Exception {

        SectorDTO sector = SectorDTO.builder()
                .id(1L)
                .name("Finance")
                .build();

        when(sectorService.findById(1L))
                .thenReturn(sector);

        mockMvc.perform(get("/api/sectors/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(1))

                .andExpect(jsonPath("$.name")
                        .value("Finance"));
    }

    @Test
    void shouldCreateSector() throws Exception {

        SectorDTO request = SectorDTO.builder()
                .name("Healthcare")
                .build();

        SectorDTO response = SectorDTO.builder()
                .id(5L)
                .name("Healthcare")
                .build();

        when(sectorService.save(any(SectorDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/sectors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id").value(5))

                .andExpect(jsonPath("$.name")
                        .value("Healthcare"));
    }

    @Test
    void shouldUpdateSector() throws Exception {

        SectorDTO request = SectorDTO.builder()
                .name("Information Technology")
                .build();

        SectorDTO response = SectorDTO.builder()
                .id(1L)
                .name("Information Technology")
                .build();

        when(sectorService.update(eq(1L), any(SectorDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/sectors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(1))

                .andExpect(jsonPath("$.name")
                        .value("Information Technology"));
    }

    @Test
    void shouldDeleteSector() throws Exception {

        mockMvc.perform(delete("/api/sectors/1"))

                .andExpect(status().isNoContent());

        verify(sectorService)
                .delete(1L);
    }

}