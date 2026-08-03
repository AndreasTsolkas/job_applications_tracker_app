package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.EmploymentTypeDTO;
import com.example.jobtracker.entity.EmploymentType;
import com.example.jobtracker.repository.EmploymentTypeRepository;


class EmploymentTypeServiceTest {


    @Mock
    private EmploymentTypeRepository employmentTypeRepository;


    private EmploymentTypeService employmentTypeService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        employmentTypeService =
                new EmploymentTypeService(employmentTypeRepository);
    }



    @Test
    void shouldReturnAllEmploymentTypes() {


        EmploymentType type1 = EmploymentType.builder()
                .id(1L)
                .name("Full Time")
                .build();



        EmploymentType type2 = EmploymentType.builder()
                .id(2L)
                .name("Part Time")
                .build();



        when(employmentTypeRepository.findAll())
                .thenReturn(List.of(type1, type2));



        List<EmploymentTypeDTO> result =
                employmentTypeService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Full Time",
                result.get(0).getName()
        );


        assertEquals(
                "Part Time",
                result.get(1).getName()
        );


        verify(employmentTypeRepository)
                .findAll();
    }



    @Test
    void shouldReturnEmploymentTypeById() {


        EmploymentType type = EmploymentType.builder()
                .id(1L)
                .name("Contract")
                .build();



        when(employmentTypeRepository.findById(1L))
                .thenReturn(Optional.of(type));



        EmploymentTypeDTO result =
                employmentTypeService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Contract",
                result.getName()
        );


        verify(employmentTypeRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenEmploymentTypeNotFound() {


        when(employmentTypeRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> employmentTypeService.findById(99L)
        );


        verify(employmentTypeRepository)
                .findById(99L);
    }



    @Test
    void shouldSaveEmploymentType() {


        EmploymentTypeDTO dto =
                EmploymentTypeDTO.builder()
                        .name("Internship")
                        .build();



        EmploymentType savedType =
                EmploymentType.builder()
                        .id(10L)
                        .name("Internship")
                        .build();



        when(employmentTypeRepository.save(any(EmploymentType.class)))
                .thenReturn(savedType);



        EmploymentTypeDTO result =
                employmentTypeService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Internship",
                result.getName()
        );


        verify(employmentTypeRepository)
                .save(any(EmploymentType.class));
    }



    @Test
    void shouldDeleteEmploymentType() {


        employmentTypeService.delete(1L);



        verify(employmentTypeRepository)
                .deleteById(1L);
    }
}