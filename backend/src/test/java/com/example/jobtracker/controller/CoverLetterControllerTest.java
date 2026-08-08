package com.example.jobtracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.example.jobtracker.DTO.CoverLetterDTO;
import com.example.jobtracker.service.CoverLetterService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CoverLetterController.class)
class CoverLetterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CoverLetterService coverLetterService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllCoverLetters() throws Exception {

        CoverLetterDTO letter = CoverLetterDTO.builder()
                .id(1L)
                .userId(1L)
                .name("Generic Cover Letter")
                .build();

        when(coverLetterService.findAll())
                .thenReturn(List.of(letter));

        mockMvc.perform(get("/api/cover-letters"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Generic Cover Letter"));
    }

    @Test
    void shouldReturnCoverLetterById() throws Exception {

        CoverLetterDTO letter = CoverLetterDTO.builder()
                .id(1L)
                .userId(1L)
                .name("Backend Role Cover Letter")
                .build();

        when(coverLetterService.findById(1L))
                .thenReturn(letter);

        mockMvc.perform(get("/api/cover-letters/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Backend Role Cover Letter"));
    }

    @Test
    void shouldReturnCoverLettersByUserId() throws Exception {

        CoverLetterDTO letter = CoverLetterDTO.builder()
                .id(2L)
                .userId(7L)
                .name("Frontend Role Cover Letter")
                .build();

        when(coverLetterService.findByUserId(7L))
                .thenReturn(List.of(letter));

        mockMvc.perform(get("/api/cover-letters/user/7"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(2))

                .andExpect(jsonPath("$[0].name")
                        .value("Frontend Role Cover Letter"));
    }

    @Test
    void shouldCreateCoverLetter() throws Exception {

        CoverLetterDTO request = CoverLetterDTO.builder()
                .userId(1L)
                .name("New Cover Letter")
                .build();

        CoverLetterDTO response = CoverLetterDTO.builder()
                .id(5L)
                .userId(1L)
                .name("New Cover Letter")
                .build();

        when(coverLetterService.save(any(CoverLetterDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/cover-letters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.name")
                        .value("New Cover Letter"));
    }

    @Test
    void shouldUpdateCoverLetter() throws Exception {

        CoverLetterDTO request = CoverLetterDTO.builder()
                .name("Backend Developer Letter v2")
                .build();

        CoverLetterDTO response = CoverLetterDTO.builder()
                .id(1L)
                .name("Backend Developer Letter v2")
                .build();

        when(coverLetterService.update(eq(1L), any(CoverLetterDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/cover-letters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Backend Developer Letter v2"));
    }

    @Test
    void shouldDeleteCoverLetter() throws Exception {

        mockMvc.perform(delete("/api/cover-letters/1"))

                .andExpect(status().isNoContent());

        verify(coverLetterService)
                .delete(1L);
    }

}
