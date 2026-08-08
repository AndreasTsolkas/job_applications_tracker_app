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

import com.example.jobtracker.DTO.ApplicationStatusHistoryDTO;
import com.example.jobtracker.service.ApplicationStatusHistoryService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ApplicationStatusHistoryController.class)
class ApplicationStatusHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ApplicationStatusHistoryService applicationStatusHistoryService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllApplicationStatusHistories() throws Exception {

        ApplicationStatusHistoryDTO history = ApplicationStatusHistoryDTO.builder()
                .id(1L)
                .applicationId(1L)
                .statusId(1L)
                .build();

        when(applicationStatusHistoryService.findAll())
                .thenReturn(List.of(history));

        mockMvc.perform(get("/api/application-status-history"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].applicationId")
                        .value(1));
    }

    @Test
    void shouldReturnApplicationStatusHistoryById() throws Exception {

        ApplicationStatusHistoryDTO history = ApplicationStatusHistoryDTO.builder()
                .id(1L)
                .applicationId(1L)
                .statusId(2L)
                .build();

        when(applicationStatusHistoryService.findById(1L))
                .thenReturn(history);

        mockMvc.perform(get("/api/application-status-history/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.statusId")
                        .value(2));
    }

    @Test
    void shouldReturnApplicationStatusHistoriesByApplicationId() throws Exception {

        ApplicationStatusHistoryDTO history = ApplicationStatusHistoryDTO.builder()
                .id(2L)
                .applicationId(9L)
                .statusId(1L)
                .build();

        when(applicationStatusHistoryService.findByApplicationId(9L))
                .thenReturn(List.of(history));

        mockMvc.perform(get("/api/application-status-history/application/9"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(2))

                .andExpect(jsonPath("$[0].applicationId")
                        .value(9));
    }

    @Test
    void shouldCreateApplicationStatusHistory() throws Exception {

        ApplicationStatusHistoryDTO request = ApplicationStatusHistoryDTO.builder()
                .applicationId(1L)
                .statusId(1L)
                .build();

        ApplicationStatusHistoryDTO response = ApplicationStatusHistoryDTO.builder()
                .id(5L)
                .applicationId(1L)
                .statusId(1L)
                .build();

        when(applicationStatusHistoryService.save(any(ApplicationStatusHistoryDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/application-status-history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.applicationId")
                        .value(1));
    }

    @Test
    void shouldUpdateApplicationStatusHistory() throws Exception {

        ApplicationStatusHistoryDTO request = ApplicationStatusHistoryDTO.builder()
                .applicationId(1L)
                .statusId(2L)
                .notes("Moved to interview stage")
                .build();

        ApplicationStatusHistoryDTO response = ApplicationStatusHistoryDTO.builder()
                .id(1L)
                .applicationId(1L)
                .statusId(2L)
                .notes("Moved to interview stage")
                .build();

        when(applicationStatusHistoryService.update(eq(1L), any(ApplicationStatusHistoryDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/application-status-history/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.statusId")
                        .value(2));
    }

    @Test
    void shouldDeleteApplicationStatusHistory() throws Exception {

        mockMvc.perform(delete("/api/application-status-history/1"))

                .andExpect(status().isNoContent());

        verify(applicationStatusHistoryService)
                .delete(1L);
    }

}
