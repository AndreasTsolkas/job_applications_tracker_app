package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.CompanyDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.entity.Sector;
import com.example.jobtracker.repository.CompanyRepository;


class CompanyServiceTest {


    @Mock
    private CompanyRepository companyRepository;


    private CompanyService companyService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        companyService =
                new CompanyService(companyRepository);
    }



    @Test
    void shouldReturnAllCompanies() {


        Sector sector = Sector.builder()
                .id(1L)
                .name("IT")
                .build();



        Company company1 = Company.builder()
                .id(10L)
                .name("Google")
                .sector(sector)
                .build();



        Company company2 = Company.builder()
                .id(20L)
                .name("Microsoft")
                .sector(sector)
                .build();



        when(companyRepository.findAll())
                .thenReturn(List.of(company1, company2));



        List<CompanyDTO> result =
                companyService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Google",
                result.get(0).getName()
        );


        assertEquals(
                1L,
                result.get(0).getSectorId()
        );


        verify(companyRepository)
                .findAll();
    }



    @Test
    void shouldReturnCompanyById() {


        Sector sector = Sector.builder()
                .id(1L)
                .name("Technology")
                .build();



        Company company = Company.builder()
                .id(10L)
                .name("Amazon")
                .sector(sector)
                .build();



        when(companyRepository.findById(10L))
                .thenReturn(Optional.of(company));



        CompanyDTO result =
                companyService.findById(10L);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Amazon",
                result.getName()
        );


        assertEquals(
                1L,
                result.getSectorId()
        );


        verify(companyRepository)
                .findById(10L);
    }



    @Test
    void shouldThrowExceptionWhenCompanyNotFound() {


        when(companyRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> companyService.findById(99L)
        );


        verify(companyRepository)
                .findById(99L);
    }



    @Test
    void shouldSaveCompany() {


        CompanyDTO dto =
                CompanyDTO.builder()
                        .name("Netflix")
                        .sectorId(1L)
                        .build();



        Company savedCompany =
                Company.builder()
                        .id(50L)
                        .name("Netflix")
                        .build();



        when(companyRepository.save(any(Company.class)))
                .thenReturn(savedCompany);



        CompanyDTO result =
                companyService.save(dto);



        assertNotNull(result);


        assertEquals(
                50L,
                result.getId()
        );


        assertEquals(
                "Netflix",
                result.getName()
        );


        verify(companyRepository)
                .save(any(Company.class));
    }



    @Test
    void shouldUpdateCompany() {


        Sector sector = Sector.builder()
                .id(1L)
                .name("IT")
                .build();


        Company existingCompany = Company.builder()
                .id(10L)
                .name("Google")
                .sector(sector)
                .build();


        CompanyDTO dto =
                CompanyDTO.builder()
                        .name("Alphabet")
                        .sectorId(1L)
                        .build();


        when(companyRepository.findById(10L))
                .thenReturn(Optional.of(existingCompany));

        when(companyRepository.save(any(Company.class)))
                .thenReturn(existingCompany);


        CompanyDTO result =
                companyService.update(10L, dto);


        assertNotNull(result);


        assertEquals(
                "Alphabet",
                result.getName()
        );


        verify(companyRepository)
                .findById(10L);

        verify(companyRepository)
                .save(any(Company.class));
    }



    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentCompany() {


        CompanyDTO dto =
                CompanyDTO.builder()
                        .name("Netflix")
                        .build();


        when(companyRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                RuntimeException.class,
                () -> companyService.update(99L, dto)
        );


        verify(companyRepository)
                .findById(99L);
    }



    @Test
    void shouldDeleteCompany() {


        companyService.delete(10L);


        verify(companyRepository)
                .deleteById(10L);
    }
}