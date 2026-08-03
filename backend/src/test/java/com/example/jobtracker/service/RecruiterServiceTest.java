package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.RecruiterDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.Recruiter;
import com.example.jobtracker.repository.RecruiterRepository;


class RecruiterServiceTest {


    @Mock
    private RecruiterRepository recruiterRepository;


    private RecruiterService recruiterService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        recruiterService =
                new RecruiterService(recruiterRepository);
    }



    @Test
    void shouldReturnAllRecruiters() {


        Company company = Company.builder()
                .id(1L)
                .name("Google")
                .build();


        Recruiter recruiter1 = Recruiter.builder()
                .id(10L)
                .firstName("John")
                .lastName("Smith")
                .email("john@google.com")
                .company(company)
                .build();


        Recruiter recruiter2 = Recruiter.builder()
                .id(20L)
                .firstName("Anna")
                .lastName("Brown")
                .email("anna@google.com")
                .company(company)
                .build();



        when(recruiterRepository.findAll())
                .thenReturn(List.of(recruiter1, recruiter2));



        List<RecruiterDTO> result =
                recruiterService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "John",
                result.get(0).getFirstName()
        );


        assertEquals(
                "Anna",
                result.get(1).getFirstName()
        );


        assertEquals(
                1L,
                result.get(0).getCompanyId()
        );


        verify(recruiterRepository)
                .findAll();
    }



    @Test
    void shouldReturnRecruiterById() {


        Company company = Company.builder()
                .id(1L)
                .name("Microsoft")
                .build();



        Recruiter recruiter = Recruiter.builder()
                .id(10L)
                .firstName("Alex")
                .lastName("Wilson")
                .email("alex@microsoft.com")
                .company(company)
                .build();



        when(recruiterRepository.findById(10L))
                .thenReturn(Optional.of(recruiter));



        RecruiterDTO result =
                recruiterService.findById(10L);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Alex",
                result.getFirstName()
        );


        assertEquals(
                1L,
                result.getCompanyId()
        );


        verify(recruiterRepository)
                .findById(10L);
    }



    @Test
    void shouldThrowExceptionWhenRecruiterNotFound() {


        when(recruiterRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> recruiterService.findById(99L)
        );


        verify(recruiterRepository)
                .findById(99L);
    }



    @Test
    void shouldReturnRecruitersByCompanyId() {


        Company company = Company.builder()
                .id(1L)
                .name("Amazon")
                .build();



        Recruiter recruiter1 = Recruiter.builder()
                .id(10L)
                .firstName("Maria")
                .lastName("Taylor")
                .company(company)
                .build();



        Recruiter recruiter2 = Recruiter.builder()
                .id(20L)
                .firstName("George")
                .lastName("Brown")
                .company(company)
                .build();



        when(recruiterRepository.findByCompanyId(1L))
                .thenReturn(List.of(recruiter1, recruiter2));



        List<RecruiterDTO> result =
                recruiterService.findByCompanyId(1L);



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Maria",
                result.get(0).getFirstName()
        );


        assertEquals(
                "George",
                result.get(1).getFirstName()
        );


        assertEquals(
                1L,
                result.get(0).getCompanyId()
        );


        verify(recruiterRepository)
                .findByCompanyId(1L);
    }



    @Test
    void shouldSaveRecruiter() {


        RecruiterDTO dto =
                RecruiterDTO.builder()
                        .firstName("Chris")
                        .lastName("Miller")
                        .companyId(1L)
                        .build();



        Recruiter savedRecruiter =
                Recruiter.builder()
                        .id(50L)
                        .firstName("Chris")
                        .lastName("Miller")
                        .build();



        when(recruiterRepository.save(any(Recruiter.class)))
                .thenReturn(savedRecruiter);



        RecruiterDTO result =
                recruiterService.save(dto);



        assertNotNull(result);


        assertEquals(
                50L,
                result.getId()
        );


        assertEquals(
                "Chris",
                result.getFirstName()
        );


        verify(recruiterRepository)
                .save(any(Recruiter.class));
    }



    @Test
    void shouldDeleteRecruiter() {


        recruiterService.delete(10L);



        verify(recruiterRepository)
                .deleteById(10L);
    }
}