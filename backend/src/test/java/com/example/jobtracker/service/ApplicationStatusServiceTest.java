package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.ApplicationStatusDTO;
import com.example.jobtracker.entity.ApplicationStatus;
import com.example.jobtracker.repository.ApplicationStatusRepository;


class ApplicationStatusServiceTest {


    @Mock
    private ApplicationStatusRepository applicationStatusRepository;


    private ApplicationStatusService applicationStatusService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        applicationStatusService =
                new ApplicationStatusService(applicationStatusRepository);
    }



    @Test
    void shouldReturnAllApplicationStatuses() {


        ApplicationStatus status1 =
                ApplicationStatus.builder()
                        .id(1L)
                        .name("Applied")
                        .build();



        ApplicationStatus status2 =
                ApplicationStatus.builder()
                        .id(2L)
                        .name("Interview")
                        .build();



        when(applicationStatusRepository.findAll())
                .thenReturn(List.of(status1, status2));



        List<ApplicationStatusDTO> result =
                applicationStatusService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Applied",
                result.get(0).getName()
        );


        assertEquals(
                "Interview",
                result.get(1).getName()
        );


        verify(applicationStatusRepository)
                .findAll();
    }



    @Test
    void shouldReturnApplicationStatusById() {


        ApplicationStatus status =
                ApplicationStatus.builder()
                        .id(1L)
                        .name("Applied")
                        .build();



        when(applicationStatusRepository.findById(1L))
                .thenReturn(Optional.of(status));



        ApplicationStatusDTO result =
                applicationStatusService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Applied",
                result.getName()
        );


        verify(applicationStatusRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenApplicationStatusNotFound() {


        when(applicationStatusRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> applicationStatusService.findById(99L)
        );


        verify(applicationStatusRepository)
                .findById(99L);
    }



    @Test
    void shouldSaveApplicationStatus() {


        ApplicationStatusDTO dto =
                ApplicationStatusDTO.builder()
                        .name("Rejected")
                        .build();



        ApplicationStatus savedStatus =
                ApplicationStatus.builder()
                        .id(10L)
                        .name("Rejected")
                        .build();



        when(applicationStatusRepository.save(any(ApplicationStatus.class)))
                .thenReturn(savedStatus);



        ApplicationStatusDTO result =
                applicationStatusService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Rejected",
                result.getName()
        );


        verify(applicationStatusRepository)
                .save(any(ApplicationStatus.class));
    }



    @Test
    void shouldUpdateApplicationStatus() {


        ApplicationStatus existingStatus =
                ApplicationStatus.builder()
                        .id(1L)
                        .name("Applied")
                        .build();


        ApplicationStatusDTO dto =
                ApplicationStatusDTO.builder()
                        .name("Application Sent")
                        .build();


        when(applicationStatusRepository.findById(1L))
                .thenReturn(Optional.of(existingStatus));

        when(applicationStatusRepository.save(any(ApplicationStatus.class)))
                .thenReturn(existingStatus);


        ApplicationStatusDTO result =
                applicationStatusService.update(1L, dto);


        assertNotNull(result);


        assertEquals(
                "Application Sent",
                result.getName()
        );


        verify(applicationStatusRepository)
                .findById(1L);

        verify(applicationStatusRepository)
                .save(any(ApplicationStatus.class));
    }



    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentApplicationStatus() {


        ApplicationStatusDTO dto =
                ApplicationStatusDTO.builder()
                        .name("Rejected")
                        .build();


        when(applicationStatusRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> applicationStatusService.update(99L, dto)
        );


        verify(applicationStatusRepository)
                .findById(99L);
    }



    @Test
    void shouldDeleteApplicationStatus() {


        applicationStatusService.delete(1L);



        verify(applicationStatusRepository)
                .deleteById(1L);
    }
}