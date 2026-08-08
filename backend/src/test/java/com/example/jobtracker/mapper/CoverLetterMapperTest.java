package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.CoverLetterDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.CoverLetter;


class CoverLetterMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        AppUser user = AppUser.builder()
                .id(10L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();


        CoverLetter coverLetter = CoverLetter.builder()
                .id(1L)
                .name("Backend Cover Letter")
                .filePath("/files/backend-letter.pdf")
                .user(user)
                .build();


        CoverLetterDTO dto =
                CoverLetterMapper.toDTO(coverLetter);


        assertNotNull(dto);


        assertEquals(
                1L,
                dto.getId()
        );


        assertEquals(
                "Backend Cover Letter",
                dto.getName()
        );


        assertEquals(
                "/files/backend-letter.pdf",
                dto.getFilePath()
        );


        assertEquals(
                10L,
                dto.getUserId()
        );
    }



    @Test
    void shouldMapDTOToEntity() {

        CoverLetterDTO dto = CoverLetterDTO.builder()
                .id(1L)
                .name("Java Cover Letter")
                .filePath("/files/java-letter.pdf")
                .userId(20L)
                .build();


        CoverLetter coverLetter =
                CoverLetterMapper.toEntity(dto);


        assertNotNull(coverLetter);


        assertEquals(
                1L,
                coverLetter.getId()
        );


        assertEquals(
                "Java Cover Letter",
                coverLetter.getName()
        );


        assertEquals(
                "/files/java-letter.pdf",
                coverLetter.getFilePath()
        );


        assertNotNull(
                coverLetter.getUser()
        );


        assertEquals(
                20L,
                coverLetter.getUser().getId()
        );
    }



    @Test
    void shouldHandleNullUserWhenMappingEntityToDTO() {

        CoverLetter coverLetter = CoverLetter.builder()
                .id(1L)
                .name("Letter without user")
                .build();


        CoverLetterDTO dto =
                CoverLetterMapper.toDTO(coverLetter);


        assertNotNull(dto);


        assertNull(
                dto.getUserId()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {

        CoverLetter coverLetter = CoverLetter.builder()
                .id(1L)
                .name("Old Letter")
                .content("Old content")
                .filePath("/files/old.pdf")
                .createdAt(LocalDateTime.now().minusDays(1))
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();

        LocalDateTime originalCreatedAt = coverLetter.getCreatedAt();

        CoverLetterDTO dto = CoverLetterDTO.builder()
                .name("New Letter")
                .content("New content")
                .filePath("/files/new.pdf")
                .userId(20L)
                .build();


        CoverLetterMapper.updateEntity(coverLetter, dto);


        assertEquals(
                "New Letter",
                coverLetter.getName()
        );

        assertEquals(
                "New content",
                coverLetter.getContent()
        );

        assertEquals(
                "/files/new.pdf",
                coverLetter.getFilePath()
        );

        assertNotNull(
                coverLetter.getUser()
        );

        assertEquals(
                20L,
                coverLetter.getUser().getId()
        );

        assertEquals(
                originalCreatedAt,
                coverLetter.getCreatedAt()
        );

        assertNotNull(coverLetter.getUpdatedAt());
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {

        CoverLetterDTO dto =
                CoverLetterMapper.toDTO(null);

        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {

        CoverLetter coverLetter =
                CoverLetterMapper.toEntity(null);

        assertNull(coverLetter);
    }
}