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

import com.example.jobtracker.DTO.JobPostingDTO;
import com.example.jobtracker.service.JobPostingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(JobPostingController.class)
class JobPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JobPostingService jobPostingService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllJobPostings() throws Exception {

        JobPostingDTO posting = JobPostingDTO.builder()
                .id(1L)
                .title("Java Developer")
                .build();

        when(jobPostingService.findAll())
                .thenReturn(List.of(posting));

        mockMvc.perform(get("/api/job-postings"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].title")
                        .value("Java Developer"));
    }

    @Test
    void shouldReturnJobPostingById() throws Exception {

        JobPostingDTO posting = JobPostingDTO.builder()
                .id(1L)
                .title("Backend Developer")
                .build();

        when(jobPostingService.findById(1L))
                .thenReturn(posting);

        mockMvc.perform(get("/api/job-postings/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.title")
                        .value("Backend Developer"));
    }

    @Test
    void shouldReturnJobPostingsByCompanyId() throws Exception {

        JobPostingDTO posting = JobPostingDTO.builder()
                .id(2L)
                .title("Software Engineer")
                .build();

        when(jobPostingService.findByCompanyId(10L))
                .thenReturn(List.of(posting));

        mockMvc.perform(
                get("/api/job-postings/company/10"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(2))

                .andExpect(jsonPath("$[0].title")
                        .value("Software Engineer"));
    }

    @Test
    void shouldReturnJobPostingsByJobRoleId() throws Exception {

        JobPostingDTO posting = JobPostingDTO.builder()
                .id(3L)
                .title("Senior Developer")
                .build();

        when(jobPostingService.findByJobRoleId(20L))
                .thenReturn(List.of(posting));

        mockMvc.perform(
                get("/api/job-postings/job-role/20"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(3))

                .andExpect(jsonPath("$[0].title")
                        .value("Senior Developer"));
    }

    @Test
    void shouldCreateJobPosting() throws Exception {

        JobPostingDTO request = JobPostingDTO.builder()
                .title("Full Stack Developer")
                .build();

        JobPostingDTO response = JobPostingDTO.builder()
                .id(5L)
                .title("Full Stack Developer")
                .build();

        when(jobPostingService.save(any(JobPostingDTO.class)))
                .thenReturn(response);

        mockMvc.perform(
                post("/api/job-postings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.title")
                        .value("Full Stack Developer"));
    }

    @Test
    void shouldDeleteJobPosting() throws Exception {

        mockMvc.perform(
                delete("/api/job-postings/1"))

                .andExpect(status().isNoContent());

        verify(jobPostingService)
                .delete(1L);
    }

}
