package com.example.jobtracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import com.example.jobtracker.DTO.CVDTO;
import com.example.jobtracker.service.CVService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CVController.class)
class CVControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CVService cvService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllCVs() throws Exception {

        CVDTO cv = CVDTO.builder()
                .id(1L)
                .userId(1L)
                .name("Main CV")
                .isActive(true)
                .build();

        when(cvService.findAll())
                .thenReturn(List.of(cv));

        mockMvc.perform(get("/api/cvs"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Main CV"));
    }

    @Test
    void shouldReturnCVById() throws Exception {

        CVDTO cv = CVDTO.builder()
                .id(1L)
                .userId(1L)
                .name("Backend CV")
                .isActive(true)
                .build();

        when(cvService.findById(1L))
                .thenReturn(cv);

        mockMvc.perform(get("/api/cvs/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Backend CV"));
    }

    @Test
    void shouldReturnCVsByUserId() throws Exception {

        CVDTO cv = CVDTO.builder()
                .id(2L)
                .userId(7L)
                .name("Frontend CV")
                .isActive(true)
                .build();

        when(cvService.findByUserId(7L))
                .thenReturn(List.of(cv));

        mockMvc.perform(get("/api/cvs/user/7"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(2))

                .andExpect(jsonPath("$[0].name")
                        .value("Frontend CV"));
    }

    @Test
    void shouldCreateCV() throws Exception {

        CVDTO request = CVDTO.builder()
                .userId(1L)
                .name("New CV")
                .isActive(true)
                .build();

        CVDTO response = CVDTO.builder()
                .id(5L)
                .userId(1L)
                .name("New CV")
                .isActive(true)
                .build();

        when(cvService.save(any(CVDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/cvs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.name")
                        .value("New CV"));
    }

    @Test
    void shouldDeleteCV() throws Exception {

        mockMvc.perform(delete("/api/cvs/1"))

                .andExpect(status().isNoContent());

        verify(cvService)
                .delete(1L);
    }

}
