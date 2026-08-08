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

import com.example.jobtracker.DTO.InterviewTypeDTO;
import com.example.jobtracker.service.InterviewTypeService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(InterviewTypeController.class)
class InterviewTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InterviewTypeService interviewTypeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllInterviewTypes() throws Exception {

        InterviewTypeDTO type = InterviewTypeDTO.builder()
                .id(1L)
                .name("Phone Screen")
                .build();

        when(interviewTypeService.findAll())
                .thenReturn(List.of(type));

        mockMvc.perform(get("/api/interview-types"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Phone Screen"));
    }

    @Test
    void shouldReturnInterviewTypeById() throws Exception {

        InterviewTypeDTO type = InterviewTypeDTO.builder()
                .id(1L)
                .name("Technical")
                .build();

        when(interviewTypeService.findById(1L))
                .thenReturn(type);

        mockMvc.perform(get("/api/interview-types/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Technical"));
    }

    @Test
    void shouldCreateInterviewType() throws Exception {

        InterviewTypeDTO request = InterviewTypeDTO.builder()
                .name("Onsite")
                .build();

        InterviewTypeDTO response = InterviewTypeDTO.builder()
                .id(5L)
                .name("Onsite")
                .build();

        when(interviewTypeService.save(any(InterviewTypeDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/interview-types")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.name")
                        .value("Onsite"));
    }

    @Test
    void shouldUpdateInterviewType() throws Exception {

        InterviewTypeDTO request = InterviewTypeDTO.builder()
                .name("Technical Screen")
                .build();

        InterviewTypeDTO response = InterviewTypeDTO.builder()
                .id(1L)
                .name("Technical Screen")
                .build();

        when(interviewTypeService.update(eq(1L), any(InterviewTypeDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/interview-types/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Technical Screen"));
    }

    @Test
    void shouldDeleteInterviewType() throws Exception {

        mockMvc.perform(delete("/api/interview-types/1"))

                .andExpect(status().isNoContent());

        verify(interviewTypeService)
                .delete(1L);
    }

}
