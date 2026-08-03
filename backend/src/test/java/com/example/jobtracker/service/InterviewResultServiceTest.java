package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.InterviewResultDTO;
import com.example.jobtracker.entity.InterviewResult;
import com.example.jobtracker.repository.InterviewResultRepository;


class InterviewResultServiceTest {


    @Mock
    private InterviewResultRepository interviewResultRepository;


    private InterviewResultService interviewResultService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        interviewResultService =
                new InterviewResultService(interviewResultRepository);
    }



    @Test
    void shouldReturnAllInterviewResults() {


        InterviewResult result1 =
                InterviewResult.builder()
                        .id(1L)
                        .name("Passed")
                        .build();



        InterviewResult result2 =
                InterviewResult.builder()
                        .id(2L)
                        .name("Rejected")
                        .build();



        when(interviewResultRepository.findAll())
                .thenReturn(List.of(result1, result2));



        List<InterviewResultDTO> result =
                interviewResultService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Passed",
                result.get(0).getName()
        );


        assertEquals(
                "Rejected",
                result.get(1).getName()
        );


        verify(interviewResultRepository)
                .findAll();
    }



    @Test
    void shouldReturnInterviewResultById() {


        InterviewResult interviewResult =
                InterviewResult.builder()
                        .id(1L)
                        .name("Passed")
                        .build();



        when(interviewResultRepository.findById(1L))
                .thenReturn(Optional.of(interviewResult));



        InterviewResultDTO result =
                interviewResultService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Passed",
                result.getName()
        );


        verify(interviewResultRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenInterviewResultNotFound() {


        when(interviewResultRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> interviewResultService.findById(99L)
        );


        verify(interviewResultRepository)
                .findById(99L);
    }



    @Test
    void shouldSaveInterviewResult() {


        InterviewResultDTO dto =
                InterviewResultDTO.builder()
                        .name("Pending")
                        .build();



        InterviewResult savedResult =
                InterviewResult.builder()
                        .id(10L)
                        .name("Pending")
                        .build();



        when(interviewResultRepository.save(any(InterviewResult.class)))
                .thenReturn(savedResult);



        InterviewResultDTO result =
                interviewResultService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Pending",
                result.getName()
        );


        verify(interviewResultRepository)
                .save(any(InterviewResult.class));
    }



    @Test
    void shouldDeleteInterviewResult() {


        interviewResultService.delete(1L);



        verify(interviewResultRepository)
                .deleteById(1L);
    }
}