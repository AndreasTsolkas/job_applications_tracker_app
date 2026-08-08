package com.example.jobtracker.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

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

import com.example.jobtracker.DTO.EmploymentTypeDTO;
import com.example.jobtracker.service.EmploymentTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(EmploymentTypeController.class)
class EmploymentTypeControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private EmploymentTypeService employmentTypeService;


    private final ObjectMapper objectMapper = new ObjectMapper();



    @Test
    void shouldReturnAllEmploymentTypes() throws Exception {


        EmploymentTypeDTO type =
                EmploymentTypeDTO.builder()
                        .id(1L)
                        .name("Full Time")
                        .build();



        when(employmentTypeService.findAll())
                .thenReturn(List.of(type));



        mockMvc.perform(get("/api/employment-types"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Full Time"));
    }



    @Test
    void shouldReturnEmploymentTypeById() throws Exception {


        EmploymentTypeDTO type =
                EmploymentTypeDTO.builder()
                        .id(1L)
                        .name("Part Time")
                        .build();



        when(employmentTypeService.findById(1L))
                .thenReturn(type);



        mockMvc.perform(get("/api/employment-types/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Part Time"));
    }



    @Test
    void shouldCreateEmploymentType() throws Exception {


        EmploymentTypeDTO request =
                EmploymentTypeDTO.builder()
                        .name("Contract")
                        .build();



        EmploymentTypeDTO response =
                EmploymentTypeDTO.builder()
                        .id(5L)
                        .name("Contract")
                        .build();



        when(employmentTypeService.save(any(EmploymentTypeDTO.class)))
                .thenReturn(response);



        mockMvc.perform(post("/api/employment-types")

                .contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(5))

                .andExpect(jsonPath("$.name")
                        .value("Contract"));
    }



    @Test
    void shouldUpdateEmploymentType() throws Exception {


        EmploymentTypeDTO request =
                EmploymentTypeDTO.builder()
                        .name("Full-Time")
                        .build();


        EmploymentTypeDTO response =
                EmploymentTypeDTO.builder()
                        .id(1L)
                        .name("Full-Time")
                        .build();


        when(employmentTypeService.update(eq(1L), any(EmploymentTypeDTO.class)))
                .thenReturn(response);


        mockMvc.perform(put("/api/employment-types/1")

                .contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Full-Time"));
    }



    @Test
    void shouldDeleteEmploymentType() throws Exception {


        mockMvc.perform(delete("/api/employment-types/1"))

                .andExpect(status().isNoContent());


        verify(employmentTypeService)
                .delete(1L);
    }

}