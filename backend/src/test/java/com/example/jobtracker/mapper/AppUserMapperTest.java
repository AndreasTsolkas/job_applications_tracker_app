package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.entity.AppUser;


class AppUserMapperTest {


    @Test
    void shouldMapEntityToDTO() {


        AppUser user = AppUser.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .userRole("USER")
                .enabled(true)
                .build();



        AppUserDTO dto =
                AppUserMapper.toDTO(user);



        assertNotNull(dto);


        assertEquals(
                1L,
                dto.getId()
        );


        assertEquals(
                "John",
                dto.getFirstName()
        );


        assertEquals(
                "Smith",
                dto.getLastName()
        );


        assertEquals(
                "john@test.com",
                dto.getEmail()
        );


        assertEquals(
                "USER",
                dto.getUserRole()
        );


        assertTrue(
                dto.getEnabled()
        );
    }



    @Test
    void shouldMapDTOToEntity() {


        AppUserDTO dto =
                AppUserDTO.builder()
                        .id(1L)
                        .firstName("Anna")
                        .lastName("Brown")
                        .email("anna@test.com")
                        .userRole("ADMIN")
                        .enabled(true)
                        .build();



        AppUser user =
                AppUserMapper.toEntity(dto);



        assertNotNull(user);


        assertEquals(
                1L,
                user.getId()
        );


        assertEquals(
                "Anna",
                user.getFirstName()
        );


        assertEquals(
                "Brown",
                user.getLastName()
        );


        assertEquals(
                "anna@test.com",
                user.getEmail()
        );


        assertEquals(
                "ADMIN",
                user.getUserRole()
        );


        assertTrue(
                user.getEnabled()
        );
    }



    @Test
    void shouldNotExposePasswordHashInDTO() {


        AppUser user = AppUser.builder()
                .id(1L)
                .email("test@test.com")
                .passwordHash("secret_hash")
                .build();



        AppUserDTO dto =
                AppUserMapper.toDTO(user);



        assertNotNull(dto);

        // Το DTO δεν έχει passwordHash
        // άρα ελέγχουμε απλά ότι το mapping ολοκληρώνεται
        assertEquals(
                "test@test.com",
                dto.getEmail()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {


        AppUser user = AppUser.builder()
                .id(1L)
                .firstName("Old")
                .lastName("Name")
                .email("old@test.com")
                .passwordHash("secret_hash")
                .userRole("USER")
                .enabled(true)
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        LocalDateTime originalCreatedAt = user.getCreatedAt();


        AppUserDTO dto = AppUserDTO.builder()
                .firstName("New")
                .lastName("User")
                .email("new@test.com")
                .build();


        AppUserMapper.updateEntity(user, dto);


        assertEquals(
                "New",
                user.getFirstName()
        );

        assertEquals(
                "User",
                user.getLastName()
        );

        assertEquals(
                "new@test.com",
                user.getEmail()
        );

        assertEquals(
                "secret_hash",
                user.getPasswordHash()
        );

        assertEquals(
                originalCreatedAt,
                user.getCreatedAt()
        );

        assertNotNull(user.getUpdatedAt());
    }



    @Test
    void shouldNotChangeUserRoleOrEnabledFlagOnUpdate() {


        AppUser user = AppUser.builder()
                .id(1L)
                .userRole("USER")
                .enabled(true)
                .build();


        AppUserDTO dto = AppUserDTO.builder()
                .userRole("ADMIN")
                .enabled(false)
                .build();


        AppUserMapper.updateEntity(user, dto);


        assertEquals(
                "USER",
                user.getUserRole()
        );

        assertTrue(
                user.getEnabled()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {


        AppUserDTO dto =
                AppUserMapper.toDTO(null);


        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {


        AppUser user =
                AppUserMapper.toEntity(null);


        assertNull(user);
    }
}