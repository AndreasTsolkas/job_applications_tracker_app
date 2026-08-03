package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.repository.AppUserRepository;


class AppUserServiceTest {


    @Mock
    private AppUserRepository appUserRepository;


    private AppUserService appUserService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        appUserService =
                new AppUserService(appUserRepository);
    }



    @Test
    void shouldReturnAllUsersAsDTOs() {


        AppUser user1 = AppUser.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .userRole("USER")
                .enabled(true)
                .build();



        AppUser user2 = AppUser.builder()
                .id(2L)
                .firstName("Anna")
                .lastName("Brown")
                .email("anna@test.com")
                .userRole("ADMIN")
                .enabled(true)
                .build();



        when(appUserRepository.findAll())
                .thenReturn(List.of(user1, user2));



        List<AppUserDTO> result =
                appUserService.getAllUsers();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "john@test.com",
                result.get(0).getEmail()
        );


        assertEquals(
                "anna@test.com",
                result.get(1).getEmail()
        );


        verify(appUserRepository)
                .findAll();
    }



    @Test
    void shouldReturnEmptyListWhenNoUsersExist() {


        when(appUserRepository.findAll())
                .thenReturn(List.of());



        List<AppUserDTO> result =
                appUserService.getAllUsers();



        assertNotNull(result);


        assertTrue(
                result.isEmpty()
        );


        verify(appUserRepository)
                .findAll();
    }
}