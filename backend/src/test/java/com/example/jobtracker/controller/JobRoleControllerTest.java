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

import com.example.jobtracker.DTO.JobRoleDTO;
import com.example.jobtracker.service.JobRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(JobRoleController.class)
class JobRoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private JobRoleService jobRoleService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllJobRoles() throws Exception {

        JobRoleDTO role = JobRoleDTO.builder()
                .id(1L)
                .name("Software Engineer")
                .build();

        when(jobRoleService.findAll())
                .thenReturn(List.of(role));

        mockMvc.perform(get("/api/job-roles"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()").value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Software Engineer"));
    }

    @Test
    void shouldReturnJobRoleById() throws Exception {

        JobRoleDTO role = JobRoleDTO.builder()
                .id(1L)
                .name("Backend Developer")
                .build();

        when(jobRoleService.findById(1L))
                .thenReturn(role);

        mockMvc.perform(get("/api/job-roles/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Backend Developer"));
    }

    @Test
    void shouldCreateJobRole() throws Exception {

        JobRoleDTO request = JobRoleDTO.builder()
                .name("Frontend Developer")
                .build();

        JobRoleDTO response = JobRoleDTO.builder()
                .id(5L)
                .name("Frontend Developer")
                .build();

        when(jobRoleService.save(any(JobRoleDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/job-roles")

                .contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.name")
                        .value("Frontend Developer"));
    }

    @Test
    void shouldUpdateJobRole() throws Exception {

        JobRoleDTO request = JobRoleDTO.builder()
                .name("Senior Backend Developer")
                .build();

        JobRoleDTO response = JobRoleDTO.builder()
                .id(1L)
                .name("Senior Backend Developer")
                .build();

        when(jobRoleService.update(eq(1L), any(JobRoleDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/job-roles/1")

                .contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Senior Backend Developer"));
    }

    @Test
    void shouldDeleteJobRole() throws Exception {

        mockMvc.perform(delete("/api/job-roles/1"))

                .andExpect(status().isNoContent());

        verify(jobRoleService)
                .delete(1L);
    }

}