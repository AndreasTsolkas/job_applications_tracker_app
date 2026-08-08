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

import com.example.jobtracker.DTO.InterviewResultDTO;
import com.example.jobtracker.service.InterviewResultService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(InterviewResultController.class)
class InterviewResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterviewResultService interviewResultService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllInterviewResults() throws Exception {

        InterviewResultDTO result = InterviewResultDTO.builder()
                .id(1L)
                .name("Passed")
                .build();

        when(interviewResultService.findAll())
                .thenReturn(List.of(result));

        mockMvc.perform(get("/api/interview-results"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Passed"));
    }

    @Test
    void shouldReturnInterviewResultById() throws Exception {

        InterviewResultDTO result = InterviewResultDTO.builder()
                .id(1L)
                .name("Failed")
                .build();

        when(interviewResultService.findById(1L))
                .thenReturn(result);

        mockMvc.perform(get("/api/interview-results/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Failed"));
    }

    @Test
    void shouldCreateInterviewResult() throws Exception {

        InterviewResultDTO request = InterviewResultDTO.builder()
                .name("Pending")
                .build();

        InterviewResultDTO response = InterviewResultDTO.builder()
                .id(5L)
                .name("Pending")
                .build();

        when(interviewResultService.save(any(InterviewResultDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/interview-results")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.name")
                        .value("Pending"));
    }

    @Test
    void shouldUpdateInterviewResult() throws Exception {

        InterviewResultDTO request = InterviewResultDTO.builder()
                .name("Passed - Moving Forward")
                .build();

        InterviewResultDTO response = InterviewResultDTO.builder()
                .id(1L)
                .name("Passed - Moving Forward")
                .build();

        when(interviewResultService.update(eq(1L), any(InterviewResultDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/interview-results/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Passed - Moving Forward"));
    }

    @Test
    void shouldDeleteInterviewResult() throws Exception {

        mockMvc.perform(delete("/api/interview-results/1"))

                .andExpect(status().isNoContent());

        verify(interviewResultService)
                .delete(1L);
    }

}
