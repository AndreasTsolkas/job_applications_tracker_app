package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.InterviewDTO;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.entity.Interview;
import com.example.jobtracker.entity.InterviewResult;
import com.example.jobtracker.entity.InterviewType;


class InterviewMapperTest {


    @Test
    void shouldMapEntityToDTO() {


        Application application = Application.builder()
                .id(10L)
                .build();


        InterviewType type = InterviewType.builder()
                .id(20L)
                .name("Technical")
                .build();


        InterviewResult result = InterviewResult.builder()
                .id(30L)
                .name("Passed")
                .build();



        Interview interview = Interview.builder()
                .id(1L)
                .application(application)
                .type(type)
                .result(result)
                .scheduledAt(LocalDateTime.now())
                .notes("Good interview")
                .build();



        InterviewDTO dto =
                InterviewMapper.toDTO(interview);



        assertNotNull(dto);



        assertEquals(
                1L,
                dto.getId()
        );


        assertEquals(
                10L,
                dto.getApplicationId()
        );


        assertEquals(
                20L,
                dto.getTypeId()
        );


        assertEquals(
                30L,
                dto.getResultId()
        );


        assertEquals(
                "Good interview",
                dto.getNotes()
        );
    }



    @Test
    void shouldMapDTOToEntity() {


        InterviewDTO dto =
                InterviewDTO.builder()
                        .id(1L)
                        .applicationId(10L)
                        .typeId(20L)
                        .resultId(30L)
                        .notes("Second interview")
                        .build();



        Interview interview =
                InterviewMapper.toEntity(dto);



        assertNotNull(interview);



        assertEquals(
                1L,
                interview.getId()
        );



        assertNotNull(
                interview.getApplication()
        );


        assertEquals(
                10L,
                interview.getApplication().getId()
        );



        assertNotNull(
                interview.getType()
        );


        assertEquals(
                20L,
                interview.getType().getId()
        );



        assertNotNull(
                interview.getResult()
        );


        assertEquals(
                30L,
                interview.getResult().getId()
        );


        assertEquals(
                "Second interview",
                interview.getNotes()
        );
    }



    @Test
    void shouldMapDTOToEntityWithoutResult() {


        InterviewDTO dto =
                InterviewDTO.builder()
                        .id(1L)
                        .applicationId(10L)
                        .typeId(20L)
                        .notes("Interview scheduled, awaiting outcome")
                        .build();



        Interview interview =
                InterviewMapper.toEntity(dto);



        assertNotNull(interview);


        assertNotNull(
                interview.getApplication()
        );

        assertNotNull(
                interview.getType()
        );


        assertNull(
                interview.getResult()
        );
    }



    @Test
    void shouldHandleNullRelationsWhenMappingEntityToDTO() {


        Interview interview = Interview.builder()
                .id(1L)
                .notes("No relations")
                .build();



        InterviewDTO dto =
                InterviewMapper.toDTO(interview);



        assertNotNull(dto);



        assertNull(
                dto.getApplicationId()
        );


        assertNull(
                dto.getTypeId()
        );


        assertNull(
                dto.getResultId()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {


        Interview interview = Interview.builder()
                .id(1L)
                .notes("Old notes")
                .createdAt(LocalDateTime.now().minusDays(1))
                .build();


        LocalDateTime originalCreatedAt = interview.getCreatedAt();

        LocalDateTime newScheduledAt = LocalDateTime.now();

        InterviewDTO dto =
                InterviewDTO.builder()
                        .applicationId(10L)
                        .typeId(20L)
                        .resultId(30L)
                        .scheduledAt(newScheduledAt)
                        .notes("Updated notes")
                        .build();


        InterviewMapper.updateEntity(interview, dto);


        assertEquals(
                "Updated notes",
                interview.getNotes()
        );


        assertEquals(
                newScheduledAt,
                interview.getScheduledAt()
        );


        assertNotNull(
                interview.getApplication()
        );


        assertEquals(
                10L,
                interview.getApplication().getId()
        );


        assertNotNull(
                interview.getType()
        );


        assertEquals(
                20L,
                interview.getType().getId()
        );


        assertNotNull(
                interview.getResult()
        );


        assertEquals(
                30L,
                interview.getResult().getId()
        );


        assertEquals(
                originalCreatedAt,
                interview.getCreatedAt()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {


        InterviewDTO dto =
                InterviewMapper.toDTO(null);


        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {


        Interview interview =
                InterviewMapper.toEntity(null);


        assertNull(interview);
    }
}