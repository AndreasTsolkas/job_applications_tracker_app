package com.example.jobtracker.controller;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import com.example.jobtracker.DTO.CompanyDTO;
import com.example.jobtracker.service.CompanyService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CompanyService companyService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllCompanies() throws Exception {

        CompanyDTO company = CompanyDTO.builder()
                .id(1L)
                .name("Google")
                .build();

        when(companyService.findAll())
                .thenReturn(List.of(company));

        mockMvc.perform(get("/api/companies"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].id")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Google"));
    }

    @Test
    void shouldReturnCompanyById() throws Exception {

        CompanyDTO company = CompanyDTO.builder()
                .id(1L)
                .name("Microsoft")
                .build();

        when(companyService.findById(1L))
                .thenReturn(company);

        mockMvc.perform(get("/api/companies/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.name")
                        .value("Microsoft"));
    }

    @Test
    void shouldReturnCompaniesBySectorId() throws Exception {

        CompanyDTO company = CompanyDTO.builder()
                .id(2L)
                .name("Amazon")
                .build();

        when(companyService.findBySectorId(5L))
                .thenReturn(List.of(company));

        mockMvc.perform(get("/api/companies/sector/5"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(1))

                .andExpect(jsonPath("$[0].name")
                        .value("Amazon"));
    }

    @Test
    void shouldCreateCompany() throws Exception {

        CompanyDTO request = CompanyDTO.builder()
                .name("Tesla")
                .build();

        CompanyDTO response = CompanyDTO.builder()
                .id(10L)
                .name("Tesla")
                .build();

        when(companyService.save(any(CompanyDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/companies")

                .contentType(MediaType.APPLICATION_JSON)

                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(10))

                .andExpect(jsonPath("$.name")
                        .value("Tesla"));
    }

    @Test
    void shouldDeleteCompany() throws Exception {

        mockMvc.perform(delete("/api/companies/1"))

                .andExpect(status().isNoContent());

        verify(companyService)
                .delete(1L);
    }

}