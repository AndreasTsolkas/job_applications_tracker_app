package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.DTO.LoginRequestDTO;
import com.example.jobtracker.DTO.LoginResponseDTO;
import com.example.jobtracker.DTO.RegisterRequestDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.repository.AppUserRepository;
import com.example.jobtracker.security.JwtService;


class AppUserServiceTest {


    @Mock
    private AppUserRepository appUserRepository;


    @Mock
    private PasswordEncoder passwordEncoder;


    @Mock
    private JwtService jwtService;


    private AppUserService appUserService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        appUserService =
                new AppUserService(
                        appUserRepository,
                        passwordEncoder,
                        jwtService
                );
    }



    private AppUser createUser(Long id) {

        return AppUser.builder()
                .id(id)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .passwordHash("hashed_password")
                .userRole("USER")
                .enabled(true)
                .build();
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



    @Test
    void shouldReturnUserById() {


        AppUser user = createUser(1L);


        when(appUserRepository.findById(1L))
                .thenReturn(Optional.of(user));


        AppUserDTO result =
                appUserService.getById(1L);


        assertNotNull(result);

        assertEquals(
                1L,
                result.getId()
        );

        assertEquals(
                "john@test.com",
                result.getEmail()
        );


        verify(appUserRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenUserNotFound() {


        when(appUserRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> appUserService.getById(99L)
        );


        verify(appUserRepository)
                .findById(99L);
    }



    @Test
    void shouldUpdateUser() {


        AppUser existingUser = createUser(1L);


        AppUserDTO dto = AppUserDTO.builder()
                .firstName("Jonathan")
                .lastName("Smith")
                .email("jonathan@test.com")
                .userRole("USER")
                .enabled(true)
                .build();


        when(appUserRepository.findById(1L))
                .thenReturn(Optional.of(existingUser));

        when(appUserRepository.save(any(AppUser.class)))
                .thenReturn(existingUser);


        AppUserDTO result =
                appUserService.update(1L, dto);


        assertNotNull(result);

        assertEquals(
                "Jonathan",
                result.getFirstName()
        );


        verify(appUserRepository)
                .findById(1L);

        verify(appUserRepository)
                .save(any(AppUser.class));
    }



    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentUser() {


        AppUserDTO dto = AppUserDTO.builder()
                .firstName("Jonathan")
                .build();


        when(appUserRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> appUserService.update(99L, dto)
        );


        verify(appUserRepository)
                .findById(99L);
    }



    @Test
    void shouldDeleteUser() {


        appUserService.delete(1L);


        verify(appUserRepository)
                .deleteById(1L);
    }



    @Test
    void shouldRegisterNewUser() {


        RegisterRequestDTO dto = RegisterRequestDTO.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .password("plain_password")
                .build();


        AppUser savedUser = createUser(1L);


        when(appUserRepository.findByEmail("john@test.com"))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode("plain_password"))
                .thenReturn("hashed_password");

        when(appUserRepository.save(any(AppUser.class)))
                .thenReturn(savedUser);


        AppUserDTO result =
                appUserService.register(dto);


        assertNotNull(result);

        assertEquals(
                "john@test.com",
                result.getEmail()
        );


        verify(appUserRepository)
                .findByEmail("john@test.com");

        verify(passwordEncoder)
                .encode("plain_password");

        verify(appUserRepository)
                .save(any(AppUser.class));
    }



    @Test
    void shouldThrowExceptionWhenRegisteringWithExistingEmail() {


        RegisterRequestDTO dto = RegisterRequestDTO.builder()
                .email("john@test.com")
                .password("plain_password")
                .build();


        when(appUserRepository.findByEmail("john@test.com"))
                .thenReturn(Optional.of(createUser(1L)));


        assertThrows(
                RuntimeException.class,
                () -> appUserService.register(dto)
        );


        verify(appUserRepository)
                .findByEmail("john@test.com");

        verify(appUserRepository, never())
                .save(any(AppUser.class));
    }



    @Test
    void shouldThrowExceptionWhenRegisteringWithNullPassword() {


        RegisterRequestDTO dto = RegisterRequestDTO.builder()
                .email("john@test.com")
                .password(null)
                .build();


        assertThrows(
                RuntimeException.class,
                () -> appUserService.register(dto)
        );


        verify(appUserRepository, never())
                .findByEmail(any());

        verify(appUserRepository, never())
                .save(any(AppUser.class));
    }



    @Test
    void shouldLoginWithValidCredentials() {


        AppUser user = createUser(1L);

        LoginRequestDTO dto = LoginRequestDTO.builder()
                .email("john@test.com")
                .password("plain_password")
                .build();


        when(appUserRepository.findByEmail("john@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("plain_password", "hashed_password"))
                .thenReturn(true);

        when(jwtService.generateToken(1L, "john@test.com", "USER"))
                .thenReturn("jwt-token");


        LoginResponseDTO result =
                appUserService.login(dto);


        assertNotNull(result);

        assertEquals(
                "jwt-token",
                result.getToken()
        );

        assertEquals(
                1L,
                result.getUserId()
        );

        assertEquals(
                "john@test.com",
                result.getEmail()
        );
    }



    @Test
    void shouldThrowExceptionWhenLoginEmailNotFound() {


        LoginRequestDTO dto = LoginRequestDTO.builder()
                .email("unknown@test.com")
                .password("plain_password")
                .build();


        when(appUserRepository.findByEmail("unknown@test.com"))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> appUserService.login(dto)
        );
    }



    @Test
    void shouldThrowExceptionWhenLoginPasswordIsInvalid() {


        AppUser user = createUser(1L);

        LoginRequestDTO dto = LoginRequestDTO.builder()
                .email("john@test.com")
                .password("wrong_password")
                .build();


        when(appUserRepository.findByEmail("john@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("wrong_password", "hashed_password"))
                .thenReturn(false);


        assertThrows(
                RuntimeException.class,
                () -> appUserService.login(dto)
        );


        verify(jwtService, never())
                .generateToken(any(), any(), any());
    }



    @Test
    void shouldThrowExceptionWhenLoginPasswordIsNull() {


        LoginRequestDTO dto = LoginRequestDTO.builder()
                .email("john@test.com")
                .password(null)
                .build();


        assertThrows(
                RuntimeException.class,
                () -> appUserService.login(dto)
        );


        verify(appUserRepository, never())
                .findByEmail(any());

        verify(jwtService, never())
                .generateToken(any(), any(), any());
    }
}
