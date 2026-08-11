package com.example.jobtracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.service.AppUserService;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AppUserController.class)
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppUserService appUserService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturnAllUsers() throws Exception {

        AppUserDTO user1 = AppUserDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();

        AppUserDTO user2 = AppUserDTO.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane@test.com")
                .build();

        when(appUserService.getAllUsers())
                .thenReturn(List.of(user1, user2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"))
                .andExpect(jsonPath("$[0].email").value("john@test.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].email").value("jane@test.com"));
    }



    @Test
    void shouldReturnUserById() throws Exception {

        AppUserDTO user = AppUserDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();

        when(appUserService.getById(1L))
                .thenReturn(user);

        mockMvc.perform(get("/api/users/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.email")
                        .value("john@test.com"));
    }



    @Test
    void shouldUpdateUser() throws Exception {

        AppUserDTO request = AppUserDTO.builder()
                .firstName("Jonathan")
                .lastName("Smith")
                .email("jonathan@test.com")
                .userRole("USER")
                .enabled(true)
                .build();

        AppUserDTO response = AppUserDTO.builder()
                .id(1L)
                .firstName("Jonathan")
                .lastName("Smith")
                .email("jonathan@test.com")
                .userRole("USER")
                .enabled(true)
                .build();

        when(appUserService.update(eq(1L), any(AppUserDTO.class)))
                .thenReturn(response);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id")
                        .value(1))

                .andExpect(jsonPath("$.firstName")
                        .value("Jonathan"));
    }



    @Test
    void shouldDeleteUser() throws Exception {

        mockMvc.perform(delete("/api/users/1"))

                .andExpect(status().isNoContent());

        verify(appUserService)
                .delete(1L);
    }

}
