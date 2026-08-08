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

import com.example.jobtracker.DTO.InterviewDTO;
import com.example.jobtracker.service.InterviewService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(InterviewController.class)
class InterviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterviewService interviewService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllInterviews() throws Exception {

        InterviewDTO interview = InterviewDTO.builder()
                .id(1L)
                .applicationId(1L)
                .typeId(1L)
                .resultId(1L)
                .build();

        when(interviewService.findAll())
                .thenReturn(List.of(interview));

        mockMvc.perform(get("/api/interviews"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].applicationId")
                        .value(1));
    }

    @Test
    void shouldReturnInterviewById() throws Exception {

        InterviewDTO interview = InterviewDTO.builder()
                .id(1L)
                .applicationId(1L)
                .typeId(2L)
                .resultId(1L)
                .build();

        when(interviewService.findById(1L))
                .thenReturn(interview);

        mockMvc.perform(get("/api/interviews/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.typeId")
                        .value(2));
    }

    @Test
    void shouldReturnInterviewsByApplicationId() throws Exception {

        InterviewDTO interview = InterviewDTO.builder()
                .id(2L)
                .applicationId(9L)
                .typeId(1L)
                .resultId(1L)
                .build();

        when(interviewService.findByApplicationId(9L))
                .thenReturn(List.of(interview));

        mockMvc.perform(get("/api/interviews/application/9"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(2))

                .andExpect(jsonPath("$[0].applicationId")
                        .value(9));
    }

    @Test
    void shouldCreateInterview() throws Exception {

        InterviewDTO request = InterviewDTO.builder()
                .applicationId(1L)
                .typeId(1L)
                .resultId(1L)
                .build();

        InterviewDTO response = InterviewDTO.builder()
                .id(5L)
                .applicationId(1L)
                .typeId(1L)
                .resultId(1L)
                .build();

        when(interviewService.save(any(InterviewDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/interviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.applicationId")
                        .value(1));
    }

    @Test
    void shouldUpdateInterview() throws Exception {

        InterviewDTO request = InterviewDTO.builder()
                .applicationId(1L)
                .typeId(1L)
                .resultId(2L)
                .notes("Rescheduled interview")
                .build();

        InterviewDTO response = InterviewDTO.builder()
                .id(1L)
                .applicationId(1L)
                .typeId(1L)
                .resultId(2L)
                .notes("Rescheduled interview")
                .build();

        when(interviewService.update(eq(1L), any(InterviewDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/interviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.resultId")
                        .value(2));
    }

    @Test
    void shouldDeleteInterview() throws Exception {

        mockMvc.perform(delete("/api/interviews/1"))

                .andExpect(status().isNoContent());

        verify(interviewService)
                .delete(1L);
    }

}
