package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.InterviewDTO;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.Interview;
import com.example.jobtracker.entity.InterviewResult;
import com.example.jobtracker.entity.InterviewType;
import com.example.jobtracker.entity.JobPosting;
import com.example.jobtracker.repository.InterviewRepository;


class InterviewServiceTest {


    @Mock
    private InterviewRepository interviewRepository;


    private InterviewService interviewService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        interviewService =
                new InterviewService(interviewRepository);
    }



    private Interview createInterview(Long id) {


        AppUser user = AppUser.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();



        JobPosting jobPosting = JobPosting.builder()
                .id(3L)
                .title("Backend Developer")
                .build();



        Application application = Application.builder()
                .id(10L)
                .user(user)
                .jobPosting(jobPosting)
                .build();



        InterviewType type = InterviewType.builder()
                .id(2L)
                .name("Technical")
                .build();



        InterviewResult result = InterviewResult.builder()
                .id(3L)
                .name("Passed")
                .build();



        return Interview.builder()
                .id(id)
                .application(application)
                .type(type)
                .result(result)
                .scheduledAt(LocalDateTime.now())
                .notes("Technical interview")
                .createdAt(LocalDateTime.now())
                .build();
    }



    @Test
    void shouldReturnAllInterviews() {


        Interview interview1 =
                createInterview(1L);


        Interview interview2 =
                createInterview(2L);



        when(interviewRepository.findAll())
                .thenReturn(List.of(interview1, interview2));



        List<InterviewDTO> result =
                interviewService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                10L,
                result.get(0).getApplicationId()
        );


        assertEquals(
                2L,
                result.get(0).getTypeId()
        );


        assertEquals(
                3L,
                result.get(0).getResultId()
        );


        verify(interviewRepository)
                .findAll();
    }



    @Test
    void shouldReturnInterviewById() {


        Interview interview =
                createInterview(1L);



        when(interviewRepository.findById(1L))
                .thenReturn(Optional.of(interview));



        InterviewDTO result =
                interviewService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                10L,
                result.getApplicationId()
        );


        assertEquals(
                2L,
                result.getTypeId()
        );


        assertEquals(
                3L,
                result.getResultId()
        );


        verify(interviewRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenInterviewNotFound() {


        when(interviewRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> interviewService.findById(99L)
        );


        verify(interviewRepository)
                .findById(99L);
    }



    @Test
    void shouldReturnInterviewsByApplicationId() {


        Interview interview =
                createInterview(1L);



        when(interviewRepository.findByApplicationId(10L))
                .thenReturn(List.of(interview));



        List<InterviewDTO> result =
                interviewService.findByApplicationId(10L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                10L,
                result.get(0).getApplicationId()
        );


        verify(interviewRepository)
                .findByApplicationId(10L);
    }



    @Test
    void shouldSaveInterview() {


        InterviewDTO dto =
                InterviewDTO.builder()
                        .applicationId(10L)
                        .typeId(2L)
                        .resultId(3L)
                        .scheduledAt(LocalDateTime.now())
                        .notes("Final interview")
                        .build();



        Interview savedInterview =
                createInterview(20L);



        when(interviewRepository.save(any(Interview.class)))
                .thenReturn(savedInterview);



        InterviewDTO result =
                interviewService.save(dto);



        assertNotNull(result);


        assertEquals(
                20L,
                result.getId()
        );


        verify(interviewRepository)
                .save(any(Interview.class));
    }



    @Test
    void shouldDeleteInterview() {


        interviewService.delete(1L);



        verify(interviewRepository)
                .deleteById(1L);
    }
}