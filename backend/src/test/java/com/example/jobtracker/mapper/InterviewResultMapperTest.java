package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.InterviewResultDTO;
import com.example.jobtracker.entity.InterviewResult;


class InterviewResultMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        InterviewResult result = InterviewResult.builder()
                .id(1L)
                .name("Passed")
                .build();


        InterviewResultDTO dto =
                InterviewResultMapper.toDTO(result);


        assertNotNull(dto);


        assertEquals(
                1L,
                dto.getId()
        );


        assertEquals(
                "Passed",
                dto.getName()
        );
    }



    @Test
    void shouldMapDTOToEntity() {


        InterviewResultDTO dto =
                InterviewResultDTO.builder()
                        .id(1L)
                        .name("Rejected")
                        .build();



        InterviewResult result =
                InterviewResultMapper.toEntity(dto);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Rejected",
                result.getName()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {


        InterviewResultDTO dto =
                InterviewResultMapper.toDTO(null);


        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {


        InterviewResult result =
                InterviewResultMapper.toEntity(null);


        assertNull(result);
    }
}