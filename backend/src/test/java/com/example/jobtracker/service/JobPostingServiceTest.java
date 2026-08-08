package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.JobPostingDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.EmploymentType;
import com.example.jobtracker.entity.JobPosting;
import com.example.jobtracker.entity.JobRole;
import com.example.jobtracker.repository.JobPostingRepository;


class JobPostingServiceTest {


    @Mock
    private JobPostingRepository jobPostingRepository;


    private JobPostingService jobPostingService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        jobPostingService =
                new JobPostingService(jobPostingRepository);
    }



    private JobPosting createJobPosting(Long id) {

        Company company = Company.builder()
                .id(1L)
                .name("Google")
                .build();


        JobRole jobRole = JobRole.builder()
                .id(2L)
                .name("Backend Developer")
                .build();


        EmploymentType employmentType = EmploymentType.builder()
                .id(3L)
                .name("Full Time")
                .build();



        return JobPosting.builder()
                .id(id)
                .title("Java Developer")
                .company(company)
                .jobRole(jobRole)
                .employmentType(employmentType)
                .build();
    }



    @Test
    void shouldReturnAllJobPostings() {


        JobPosting posting1 = createJobPosting(1L);
        JobPosting posting2 = createJobPosting(2L);



        when(jobPostingRepository.findAll())
                .thenReturn(List.of(posting1, posting2));



        List<JobPostingDTO> result =
                jobPostingService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Java Developer",
                result.get(0).getTitle()
        );


        assertEquals(
                1L,
                result.get(0).getCompanyId()
        );


        assertEquals(
                2L,
                result.get(0).getJobRoleId()
        );


        assertEquals(
                3L,
                result.get(0).getEmploymentTypeId()
        );


        verify(jobPostingRepository)
                .findAll();
    }



    @Test
    void shouldReturnJobPostingById() {


        JobPosting posting =
                createJobPosting(1L);



        when(jobPostingRepository.findById(1L))
                .thenReturn(Optional.of(posting));



        JobPostingDTO result =
                jobPostingService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Java Developer",
                result.getTitle()
        );


        assertEquals(
                1L,
                result.getCompanyId()
        );


        verify(jobPostingRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenJobPostingNotFound() {


        when(jobPostingRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> jobPostingService.findById(99L)
        );


        verify(jobPostingRepository)
                .findById(99L);
    }



    @Test
    void shouldReturnJobPostingsByCompanyId() {


        JobPosting posting =
                createJobPosting(1L);



        when(jobPostingRepository.findByCompanyId(1L))
                .thenReturn(List.of(posting));



        List<JobPostingDTO> result =
                jobPostingService.findByCompanyId(1L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                1L,
                result.get(0).getCompanyId()
        );


        verify(jobPostingRepository)
                .findByCompanyId(1L);
    }



    @Test
    void shouldReturnJobPostingsByJobRoleId() {


        JobPosting posting =
                createJobPosting(1L);



        when(jobPostingRepository.findByJobRoleId(2L))
                .thenReturn(List.of(posting));



        List<JobPostingDTO> result =
                jobPostingService.findByJobRoleId(2L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                2L,
                result.get(0).getJobRoleId()
        );


        verify(jobPostingRepository)
                .findByJobRoleId(2L);
    }



    @Test
    void shouldSaveJobPosting() {


        JobPostingDTO dto =
                JobPostingDTO.builder()
                        .title("Backend Engineer")
                        .companyId(1L)
                        .jobRoleId(2L)
                        .employmentTypeId(3L)
                        .build();



        JobPosting savedPosting =
                createJobPosting(10L);



        when(jobPostingRepository.save(any(JobPosting.class)))
                .thenReturn(savedPosting);



        JobPostingDTO result =
                jobPostingService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Java Developer",
                result.getTitle()
        );


        verify(jobPostingRepository)
                .save(any(JobPosting.class));
    }



    @Test
    void shouldUpdateJobPosting() {


        JobPosting existingPosting =
                createJobPosting(1L);


        JobPostingDTO dto =
                JobPostingDTO.builder()
                        .title("Senior Java Developer")
                        .companyId(1L)
                        .jobRoleId(2L)
                        .employmentTypeId(3L)
                        .build();


        when(jobPostingRepository.findById(1L))
                .thenReturn(Optional.of(existingPosting));

        when(jobPostingRepository.save(any(JobPosting.class)))
                .thenReturn(existingPosting);


        JobPostingDTO result =
                jobPostingService.update(1L, dto);


        assertNotNull(result);


        assertEquals(
                "Senior Java Developer",
                result.getTitle()
        );


        verify(jobPostingRepository)
                .findById(1L);

        verify(jobPostingRepository)
                .save(any(JobPosting.class));
    }



    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentJobPosting() {


        JobPostingDTO dto =
                JobPostingDTO.builder()
                        .title("Backend Engineer")
                        .build();


        when(jobPostingRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> jobPostingService.update(99L, dto)
        );


        verify(jobPostingRepository)
                .findById(99L);
    }



    @Test
    void shouldDeleteJobPosting() {


        jobPostingService.delete(1L);


        verify(jobPostingRepository)
                .deleteById(1L);
    }
}