package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.JobRoleDTO;
import com.example.jobtracker.entity.JobRole;
import com.example.jobtracker.repository.JobRoleRepository;


class JobRoleServiceTest {


    @Mock
    private JobRoleRepository jobRoleRepository;


    private JobRoleService jobRoleService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        jobRoleService =
                new JobRoleService(jobRoleRepository);
    }



    @Test
    void shouldReturnAllJobRoles() {


        JobRole role1 = JobRole.builder()
                .id(1L)
                .name("Backend Developer")
                .build();



        JobRole role2 = JobRole.builder()
                .id(2L)
                .name("Frontend Developer")
                .build();



        when(jobRoleRepository.findAll())
                .thenReturn(List.of(role1, role2));



        List<JobRoleDTO> result =
                jobRoleService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Backend Developer",
                result.get(0).getName()
        );


        assertEquals(
                "Frontend Developer",
                result.get(1).getName()
        );


        verify(jobRoleRepository)
                .findAll();
    }



    @Test
    void shouldReturnJobRoleById() {


        JobRole role = JobRole.builder()
                .id(1L)
                .name("Java Developer")
                .build();



        when(jobRoleRepository.findById(1L))
                .thenReturn(Optional.of(role));



        JobRoleDTO result =
                jobRoleService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Java Developer",
                result.getName()
        );


        verify(jobRoleRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenJobRoleNotFound() {


        when(jobRoleRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> jobRoleService.findById(99L)
        );


        verify(jobRoleRepository)
                .findById(99L);
    }



    @Test
    void shouldSaveJobRole() {


        JobRoleDTO dto =
                JobRoleDTO.builder()
                        .name("Data Engineer")
                        .build();



        JobRole savedJobRole =
                JobRole.builder()
                        .id(10L)
                        .name("Data Engineer")
                        .build();



        when(jobRoleRepository.save(any(JobRole.class)))
                .thenReturn(savedJobRole);



        JobRoleDTO result =
                jobRoleService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Data Engineer",
                result.getName()
        );


        verify(jobRoleRepository)
                .save(any(JobRole.class));
    }



    @Test
    void shouldUpdateJobRole() {


        JobRole existingRole = JobRole.builder()
                .id(1L)
                .name("Backend Developer")
                .build();


        JobRoleDTO dto =
                JobRoleDTO.builder()
                        .name("Senior Backend Developer")
                        .build();


        when(jobRoleRepository.findById(1L))
                .thenReturn(Optional.of(existingRole));

        when(jobRoleRepository.save(any(JobRole.class)))
                .thenReturn(existingRole);


        JobRoleDTO result =
                jobRoleService.update(1L, dto);


        assertNotNull(result);


        assertEquals(
                "Senior Backend Developer",
                result.getName()
        );


        verify(jobRoleRepository)
                .findById(1L);

        verify(jobRoleRepository)
                .save(any(JobRole.class));
    }



    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentJobRole() {


        JobRoleDTO dto =
                JobRoleDTO.builder()
                        .name("Data Engineer")
                        .build();


        when(jobRoleRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> jobRoleService.update(99L, dto)
        );


        verify(jobRoleRepository)
                .findById(99L);
    }



    @Test
    void shouldDeleteJobRole() {


        jobRoleService.delete(1L);



        verify(jobRoleRepository)
                .deleteById(1L);
    }
}