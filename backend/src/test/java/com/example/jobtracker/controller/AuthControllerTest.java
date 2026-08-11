package com.example.jobtracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.DTO.LoginRequestDTO;
import com.example.jobtracker.DTO.LoginResponseDTO;
import com.example.jobtracker.DTO.RegisterRequestDTO;
import com.example.jobtracker.service.AppUserService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppUserService appUserService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldRegisterNewUser() throws Exception {

        RegisterRequestDTO request = RegisterRequestDTO.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .password("plain_password")
                .build();

        AppUserDTO response = AppUserDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .userRole("USER")
                .enabled(true)
                .build();

        when(appUserService.register(any(RegisterRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.email")
                        .value("john@test.com"));
    }



    @Test
    void shouldLoginUser() throws Exception {

        LoginRequestDTO request = LoginRequestDTO.builder()
                .email("john@test.com")
                .password("plain_password")
                .build();

        LoginResponseDTO response = LoginResponseDTO.builder()
                .token("jwt-token")
                .userId(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .userRole("USER")
                .build();

        when(appUserService.login(any(LoginRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.token")
                        .value("jwt-token"))

                .andExpect(jsonPath("$.userId")
                        .value(1));
    }

}
