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

import com.example.jobtracker.DTO.ApplicationStatusHistoryDTO;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.entity.ApplicationStatus;
import com.example.jobtracker.entity.ApplicationStatusHistory;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.JobPosting;
import com.example.jobtracker.repository.ApplicationStatusHistoryRepository;


class ApplicationStatusHistoryServiceTest {


    @Mock
    private ApplicationStatusHistoryRepository applicationStatusHistoryRepository;


    private ApplicationStatusHistoryService applicationStatusHistoryService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        applicationStatusHistoryService =
                new ApplicationStatusHistoryService(
                        applicationStatusHistoryRepository
                );
    }



    private ApplicationStatusHistory createHistory(Long id) {


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



        Application application =
                Application.builder()
                        .id(10L)
                        .user(user)
                        .jobPosting(jobPosting)
                        .build();



        ApplicationStatus status =
                ApplicationStatus.builder()
                        .id(2L)
                        .name("Applied")
                        .build();



        return ApplicationStatusHistory.builder()
                .id(id)
                .application(application)
                .status(status)
                .changedAt(LocalDateTime.now())
                .build();
    }



    @Test
    void shouldReturnAllHistoryRecords() {


        ApplicationStatusHistory history1 =
                createHistory(1L);


        ApplicationStatusHistory history2 =
                createHistory(2L);



        when(applicationStatusHistoryRepository.findAll())
                .thenReturn(List.of(history1, history2));



        List<ApplicationStatusHistoryDTO> result =
                applicationStatusHistoryService.findAll();



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
                result.get(0).getStatusId()
        );


        verify(applicationStatusHistoryRepository)
                .findAll();
    }



    @Test
    void shouldReturnHistoryById() {


        ApplicationStatusHistory history =
                createHistory(1L);



        when(applicationStatusHistoryRepository.findById(1L))
                .thenReturn(Optional.of(history));



        ApplicationStatusHistoryDTO result =
                applicationStatusHistoryService.findById(1L);



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
                result.getStatusId()
        );


        verify(applicationStatusHistoryRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenHistoryNotFound() {


        when(applicationStatusHistoryRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> applicationStatusHistoryService.findById(99L)
        );


        verify(applicationStatusHistoryRepository)
                .findById(99L);
    }



    @Test
    void shouldReturnHistoryByApplicationId() {


        ApplicationStatusHistory history =
                createHistory(1L);



        when(applicationStatusHistoryRepository.findByApplicationId(10L))
                .thenReturn(List.of(history));



        List<ApplicationStatusHistoryDTO> result =
                applicationStatusHistoryService.findByApplicationId(10L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                10L,
                result.get(0).getApplicationId()
        );


        verify(applicationStatusHistoryRepository)
                .findByApplicationId(10L);
    }



    @Test
    void shouldSaveHistory() {


        ApplicationStatusHistoryDTO dto =
                ApplicationStatusHistoryDTO.builder()
                        .applicationId(10L)
                        .statusId(2L)
                        .build();



        ApplicationStatusHistory savedHistory =
                createHistory(20L);



        when(applicationStatusHistoryRepository.save(any(ApplicationStatusHistory.class)))
                .thenReturn(savedHistory);



        ApplicationStatusHistoryDTO result =
                applicationStatusHistoryService.save(dto);



        assertNotNull(result);


        assertEquals(
                20L,
                result.getId()
        );


        verify(applicationStatusHistoryRepository)
                .save(any(ApplicationStatusHistory.class));
    }



    @Test
    void shouldDeleteHistory() {


        applicationStatusHistoryService.delete(1L);



        verify(applicationStatusHistoryRepository)
                .deleteById(1L);
    }
}