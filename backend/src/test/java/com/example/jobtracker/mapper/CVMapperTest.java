package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.CVDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.CV;


class CVMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        AppUser user = AppUser.builder()
                .id(10L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();


        CV cv = CV.builder()
                .id(1L)
                .name("Backend CV")
                .filePath("/files/backend-cv.pdf")
                .isActive(true)
                .user(user)
                .build();


        CVDTO dto = CVMapper.toDTO(cv);


        assertNotNull(dto);

        assertEquals(
                1L,
                dto.getId()
        );

        assertEquals(
                "Backend CV",
                dto.getName()
        );

        assertEquals(
                "/files/backend-cv.pdf",
                dto.getFilePath()
        );

        assertTrue(
                dto.getIsActive()
        );


        assertEquals(
                10L,
                dto.getUserId()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        CVDTO dto = CVDTO.builder()
                .id(1L)
                .name("Java CV")
                .filePath("/files/java-cv.pdf")
                .isActive(true)
                .userId(20L)
                .build();


        CV cv = CVMapper.toEntity(dto);


        assertNotNull(cv);


        assertEquals(
                1L,
                cv.getId()
        );


        assertEquals(
                "Java CV",
                cv.getName()
        );


        assertEquals(
                "/files/java-cv.pdf",
                cv.getFilePath()
        );


        assertTrue(
                cv.getIsActive()
        );


        assertNotNull(
                cv.getUser()
        );


        assertEquals(
                20L,
                cv.getUser().getId()
        );
    }



    @Test
    void shouldHandleNullUserWhenMappingEntityToDTO() {

        CV cv = CV.builder()
                .id(1L)
                .name("CV without user")
                .build();


        CVDTO dto = CVMapper.toDTO(cv);


        assertNotNull(dto);

        assertNull(
                dto.getUserId()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {

        CV cv = CV.builder()
                .id(1L)
                .name("Old CV")
                .filePath("/files/old.pdf")
                .isActive(false)
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        LocalDateTime originalCreatedAt = cv.getCreatedAt();

        CVDTO dto = CVDTO.builder()
                .name("New CV")
                .filePath("/files/new.pdf")
                .isActive(true)
                .userId(20L)
                .build();


        CVMapper.updateEntity(cv, dto);


        assertEquals(
                "New CV",
                cv.getName()
        );

        assertEquals(
                "/files/new.pdf",
                cv.getFilePath()
        );

        assertTrue(
                cv.getIsActive()
        );

        assertNotNull(
                cv.getUser()
        );

        assertEquals(
                20L,
                cv.getUser().getId()
        );

        assertEquals(
                originalCreatedAt,
                cv.getCreatedAt()
        );

        assertNotNull(cv.getUpdatedAt());
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        CVDTO dto = CVMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        CV cv = CVMapper.toEntity(null);

        assertNull(cv);
    }
}