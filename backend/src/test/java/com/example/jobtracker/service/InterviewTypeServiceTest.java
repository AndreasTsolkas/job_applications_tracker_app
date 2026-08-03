package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.InterviewTypeDTO;
import com.example.jobtracker.entity.InterviewType;
import com.example.jobtracker.repository.InterviewTypeRepository;


class InterviewTypeServiceTest {


    @Mock
    private InterviewTypeRepository interviewTypeRepository;


    private InterviewTypeService interviewTypeService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        interviewTypeService =
                new InterviewTypeService(interviewTypeRepository);
    }



    @Test
    void shouldReturnAllInterviewTypes() {


        InterviewType type1 =
                InterviewType.builder()
                        .id(1L)
                        .name("Technical")
                        .build();



        InterviewType type2 =
                InterviewType.builder()
                        .id(2L)
                        .name("HR")
                        .build();



        when(interviewTypeRepository.findAll())
                .thenReturn(List.of(type1, type2));



        List<InterviewTypeDTO> result =
                interviewTypeService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Technical",
                result.get(0).getName()
        );


        assertEquals(
                "HR",
                result.get(1).getName()
        );


        verify(interviewTypeRepository)
                .findAll();
    }



    @Test
    void shouldReturnInterviewTypeById() {


        InterviewType type =
                InterviewType.builder()
                        .id(1L)
                        .name("Technical")
                        .build();



        when(interviewTypeRepository.findById(1L))
                .thenReturn(Optional.of(type));



        InterviewTypeDTO result =
                interviewTypeService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Technical",
                result.getName()
        );


        verify(interviewTypeRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenInterviewTypeNotFound() {


        when(interviewTypeRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> interviewTypeService.findById(99L)
        );


        verify(interviewTypeRepository)
                .findById(99L);
    }



    @Test
    void shouldSaveInterviewType() {


        InterviewTypeDTO dto =
                InterviewTypeDTO.builder()
                        .name("Final Interview")
                        .build();



        InterviewType savedType =
                InterviewType.builder()
                        .id(10L)
                        .name("Final Interview")
                        .build();



        when(interviewTypeRepository.save(any(InterviewType.class)))
                .thenReturn(savedType);



        InterviewTypeDTO result =
                interviewTypeService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Final Interview",
                result.getName()
        );


        verify(interviewTypeRepository)
                .save(any(InterviewType.class));
    }



    @Test
    void shouldDeleteInterviewType() {


        interviewTypeService.delete(1L);



        verify(interviewTypeRepository)
                .deleteById(1L);
    }
}