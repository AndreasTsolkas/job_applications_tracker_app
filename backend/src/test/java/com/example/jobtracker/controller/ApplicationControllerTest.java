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

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import com.example.jobtracker.DTO.ApplicationDTO;
import com.example.jobtracker.service.ApplicationService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationService applicationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllApplications() throws Exception {

        ApplicationDTO application = ApplicationDTO.builder()
                .id(1L)
                .userId(1L)
                .jobPostingId(1L)
                .statusId(1L)
                .appliedDate(LocalDate.of(2026, 1, 15))
                .build();

        when(applicationService.findAll())
                .thenReturn(List.of(application));

        mockMvc.perform(get("/api/applications"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].userId")
                        .value(1));
    }

    @Test
    void shouldReturnApplicationById() throws Exception {

        ApplicationDTO application = ApplicationDTO.builder()
                .id(1L)
                .userId(1L)
                .jobPostingId(2L)
                .statusId(1L)
                .build();

        when(applicationService.findById(1L))
                .thenReturn(application);

        mockMvc.perform(get("/api/applications/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.jobPostingId")
                        .value(2));
    }

    @Test
    void shouldReturnApplicationsByUserId() throws Exception {

        ApplicationDTO application = ApplicationDTO.builder()
                .id(2L)
                .userId(7L)
                .jobPostingId(3L)
                .statusId(1L)
                .build();

        when(applicationService.findByUserId(7L))
                .thenReturn(List.of(application));

        mockMvc.perform(get("/api/applications/user/7"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(2))

                .andExpect(jsonPath("$[0].userId")
                        .value(7));
    }

    @Test
    void shouldReturnApplicationsByStatusId() throws Exception {

        ApplicationDTO application = ApplicationDTO.builder()
                .id(3L)
                .userId(1L)
                .jobPostingId(4L)
                .statusId(2L)
                .build();

        when(applicationService.findByStatusId(2L))
                .thenReturn(List.of(application));

        mockMvc.perform(get("/api/applications/status/2"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(3))

                .andExpect(jsonPath("$[0].statusId")
                        .value(2));
    }

    @Test
    void shouldReturnApplicationsByJobPostingId() throws Exception {

        ApplicationDTO application = ApplicationDTO.builder()
                .id(4L)
                .userId(1L)
                .jobPostingId(9L)
                .statusId(1L)
                .build();

        when(applicationService.findByJobPostingId(9L))
                .thenReturn(List.of(application));

        mockMvc.perform(get("/api/applications/job-posting/9"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(4))

                .andExpect(jsonPath("$[0].jobPostingId")
                        .value(9));
    }

    @Test
    void shouldCreateApplication() throws Exception {

        ApplicationDTO request = ApplicationDTO.builder()
                .userId(1L)
                .jobPostingId(1L)
                .statusId(1L)
                .build();

        ApplicationDTO response = ApplicationDTO.builder()
                .id(5L)
                .userId(1L)
                .jobPostingId(1L)
                .statusId(1L)
                .build();

        when(applicationService.save(any(ApplicationDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.userId")
                        .value(1));
    }

    @Test
    void shouldUpdateApplication() throws Exception {

        ApplicationDTO request = ApplicationDTO.builder()
                .userId(1L)
                .jobPostingId(1L)
                .statusId(2L)
                .notes("Updated notes")
                .build();

        ApplicationDTO response = ApplicationDTO.builder()
                .id(1L)
                .userId(1L)
                .jobPostingId(1L)
                .statusId(2L)
                .notes("Updated notes")
                .build();

        when(applicationService.update(eq(1L), any(ApplicationDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/applications/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.statusId")
                        .value(2));
    }

    @Test
    void shouldDeleteApplication() throws Exception {

        mockMvc.perform(delete("/api/applications/1"))

                .andExpect(status().isNoContent());

        verify(applicationService)
                .delete(1L);
    }

}
