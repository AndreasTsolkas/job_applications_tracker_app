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

import com.example.jobtracker.DTO.RecruiterDTO;
import com.example.jobtracker.service.RecruiterService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RecruiterController.class)
class RecruiterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecruiterService recruiterService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllRecruiters() throws Exception {

        RecruiterDTO recruiter = RecruiterDTO.builder()
                .id(1L)
                .firstName("Alice")
                .lastName("Brown")
                .email("alice@company.com")
                .companyId(10L)
                .build();

        when(recruiterService.findAll())
                .thenReturn(List.of(recruiter));

        mockMvc.perform(get("/api/recruiters"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].firstName")
                        .value("Alice"))

                .andExpect(jsonPath("$[0].lastName")
                        .value("Brown"));
    }

    @Test
    void shouldReturnRecruiterById() throws Exception {

        RecruiterDTO recruiter = RecruiterDTO.builder()
                .id(1L)
                .firstName("Bob")
                .lastName("White")
                .email("bob@company.com")
                .companyId(10L)
                .build();

        when(recruiterService.findById(1L))
                .thenReturn(recruiter);

        mockMvc.perform(get("/api/recruiters/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.firstName")
                        .value("Bob"))

                .andExpect(jsonPath("$.email")
                        .value("bob@company.com"));
    }

    @Test
    void shouldReturnRecruitersByCompanyId() throws Exception {

        RecruiterDTO recruiter = RecruiterDTO.builder()
                .id(2L)
                .firstName("Carol")
                .lastName("Green")
                .companyId(10L)
                .build();

        when(recruiterService.findByCompanyId(10L))
                .thenReturn(List.of(recruiter));

        mockMvc.perform(get("/api/recruiters/company/10"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(2))

                .andExpect(jsonPath("$[0].firstName")
                        .value("Carol"));
    }

    @Test
    void shouldCreateRecruiter() throws Exception {

        RecruiterDTO request = RecruiterDTO.builder()
                .firstName("David")
                .lastName("Black")
                .email("david@company.com")
                .companyId(10L)
                .build();

        RecruiterDTO response = RecruiterDTO.builder()
                .id(5L)
                .firstName("David")
                .lastName("Black")
                .email("david@company.com")
                .companyId(10L)
                .build();

        when(recruiterService.save(any(RecruiterDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/recruiters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.firstName")
                        .value("David"));
    }

    @Test
    void shouldUpdateRecruiter() throws Exception {

        RecruiterDTO request = RecruiterDTO.builder()
                .firstName("Jonathan")
                .lastName("Black")
                .companyId(10L)
                .build();

        RecruiterDTO response = RecruiterDTO.builder()
                .id(1L)
                .firstName("Jonathan")
                .lastName("Black")
                .companyId(10L)
                .build();

        when(recruiterService.update(eq(1L), any(RecruiterDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/recruiters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.firstName")
                        .value("Jonathan"));
    }

    @Test
    void shouldDeleteRecruiter() throws Exception {

        mockMvc.perform(delete("/api/recruiters/1"))

                .andExpect(status().isNoContent());

        verify(recruiterService)
                .delete(1L);
    }

}
