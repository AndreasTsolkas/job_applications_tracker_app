package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.ApplicationDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.entity.ApplicationStatus;
import com.example.jobtracker.entity.JobPosting;
import com.example.jobtracker.repository.ApplicationRepository;


class ApplicationServiceTest {


    @Mock
    private ApplicationRepository applicationRepository;


    private ApplicationService applicationService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        applicationService =
                new ApplicationService(applicationRepository);
    }



    private Application createApplication(Long id) {


        AppUser user = AppUser.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();



        ApplicationStatus status = ApplicationStatus.builder()
                .id(2L)
                .name("Applied")
                .build();



        JobPosting jobPosting = JobPosting.builder()
                .id(3L)
                .title("Backend Developer")
                .build();



        return Application.builder()
                .id(id)
                .user(user)
                .status(status)
                .jobPosting(jobPosting)
                .build();
    }



    @Test
    void shouldReturnAllApplications() {


        Application app1 = createApplication(1L);
        Application app2 = createApplication(2L);



        when(applicationRepository.findAll())
                .thenReturn(List.of(app1, app2));



        List<ApplicationDTO> result =
                applicationService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                1L,
                result.get(0).getUserId()
        );


        assertEquals(
                2L,
                result.get(0).getStatusId()
        );


        assertEquals(
                3L,
                result.get(0).getJobPostingId()
        );


        verify(applicationRepository)
                .findAll();
    }



    @Test
    void shouldReturnApplicationById() {


        Application application =
                createApplication(1L);



        when(applicationRepository.findById(1L))
                .thenReturn(Optional.of(application));



        ApplicationDTO result =
                applicationService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                1L,
                result.getUserId()
        );


        verify(applicationRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenApplicationNotFound() {


        when(applicationRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> applicationService.findById(99L)
        );


        verify(applicationRepository)
                .findById(99L);
    }



    @Test
    void shouldReturnApplicationsByUserId() {


        Application application =
                createApplication(1L);



        when(applicationRepository.findByUserId(1L))
                .thenReturn(List.of(application));



        List<ApplicationDTO> result =
                applicationService.findByUserId(1L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                1L,
                result.get(0).getUserId()
        );


        verify(applicationRepository)
                .findByUserId(1L);
    }



    @Test
    void shouldReturnApplicationsByStatusId() {


        Application application =
                createApplication(1L);



        when(applicationRepository.findByStatusId(2L))
                .thenReturn(List.of(application));



        List<ApplicationDTO> result =
                applicationService.findByStatusId(2L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                2L,
                result.get(0).getStatusId()
        );


        verify(applicationRepository)
                .findByStatusId(2L);
    }



    @Test
    void shouldReturnApplicationsByJobPostingId() {


        Application application =
                createApplication(1L);



        when(applicationRepository.findByJobPostingId(3L))
                .thenReturn(List.of(application));



        List<ApplicationDTO> result =
                applicationService.findByJobPostingId(3L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                3L,
                result.get(0).getJobPostingId()
        );


        verify(applicationRepository)
                .findByJobPostingId(3L);
    }



    @Test
    void shouldSaveApplication() {


        ApplicationDTO dto =
                ApplicationDTO.builder()
                        .userId(1L)
                        .statusId(2L)
                        .jobPostingId(3L)
                        .build();



        Application savedApplication =
                createApplication(10L);



        when(applicationRepository.save(any(Application.class)))
                .thenReturn(savedApplication);



        ApplicationDTO result =
                applicationService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        verify(applicationRepository)
                .save(any(Application.class));
    }



    @Test
    void shouldDeleteApplication() {


        applicationService.delete(1L);



        verify(applicationRepository)
                .deleteById(1L);
    }
}