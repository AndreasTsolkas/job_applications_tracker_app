package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.InterviewTypeDTO;
import com.example.jobtracker.entity.InterviewType;


class InterviewTypeMapperTest {


    @Test
    void shouldMapEntityToDTO() {

        InterviewType type = InterviewType.builder()
                .id(1L)
                .name("Technical Interview")
                .build();


        InterviewTypeDTO dto =
                InterviewTypeMapper.toDTO(type);


        assertNotNull(dto);


        assertEquals(
                1L,
                dto.getId()
        );


        assertEquals(
                "Technical Interview",
                dto.getName()
        );
    }



    @Test
    void shouldMapDTOToEntity() {


        InterviewTypeDTO dto =
                InterviewTypeDTO.builder()
                        .id(1L)
                        .name("HR Interview")
                        .build();



        InterviewType type =
                InterviewTypeMapper.toEntity(dto);



        assertNotNull(type);


        assertEquals(
                1L,
                type.getId()
        );


        assertEquals(
                "HR Interview",
                type.getName()
        );
    }



    @Test
    void shouldUpdateEntityFromDTO() {


        InterviewType type = InterviewType.builder()
                .id(1L)
                .name("Technical Interview")
                .build();


        InterviewTypeDTO dto =
                InterviewTypeDTO.builder()
                        .name("Technical Screen")
                        .build();


        InterviewTypeMapper.updateEntity(type, dto);


        assertEquals(
                "Technical Screen",
                type.getName()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {


        InterviewTypeDTO dto =
                InterviewTypeMapper.toDTO(null);


        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {


        InterviewType type =
                InterviewTypeMapper.toEntity(null);


        assertNull(type);
    }
}