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

import com.example.jobtracker.DTO.ApplicationStatusDTO;
import com.example.jobtracker.service.ApplicationStatusService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ApplicationStatusController.class)
class ApplicationStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationStatusService applicationStatusService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllApplicationStatuses() throws Exception {

        ApplicationStatusDTO status = ApplicationStatusDTO.builder()
                .id(1L)
                .name("Applied")
                .build();

        when(applicationStatusService.findAll())
                .thenReturn(List.of(status));

        mockMvc.perform(get("/api/application-statuses"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Applied"));
    }

    @Test
    void shouldReturnApplicationStatusById() throws Exception {

        ApplicationStatusDTO status = ApplicationStatusDTO.builder()
                .id(1L)
                .name("Interviewing")
                .build();

        when(applicationStatusService.findById(1L))
                .thenReturn(status);

        mockMvc.perform(get("/api/application-statuses/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Interviewing"));
    }

    @Test
    void shouldCreateApplicationStatus() throws Exception {

        ApplicationStatusDTO request = ApplicationStatusDTO.builder()
                .name("Offered")
                .build();

        ApplicationStatusDTO response = ApplicationStatusDTO.builder()
                .id(5L)
                .name("Offered")
                .build();

        when(applicationStatusService.save(any(ApplicationStatusDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/application-statuses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.name")
                        .value("Offered"));
    }

    @Test
    void shouldUpdateApplicationStatus() throws Exception {

        ApplicationStatusDTO request = ApplicationStatusDTO.builder()
                .name("Application Sent")
                .build();

        ApplicationStatusDTO response = ApplicationStatusDTO.builder()
                .id(1L)
                .name("Application Sent")
                .build();

        when(applicationStatusService.update(eq(1L), any(ApplicationStatusDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/application-statuses/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Application Sent"));
    }

    @Test
    void shouldDeleteApplicationStatus() throws Exception {

        mockMvc.perform(delete("/api/application-statuses/1"))

                .andExpect(status().isNoContent());

        verify(applicationStatusService)
                .delete(1L);
    }

}
