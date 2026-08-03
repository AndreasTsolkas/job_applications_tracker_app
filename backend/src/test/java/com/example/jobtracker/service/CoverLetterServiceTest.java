package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.CoverLetterDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.CoverLetter;
import com.example.jobtracker.repository.CoverLetterRepository;


class CoverLetterServiceTest {


    @Mock
    private CoverLetterRepository coverLetterRepository;


    private CoverLetterService coverLetterService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        coverLetterService =
                new CoverLetterService(coverLetterRepository);
    }



    private CoverLetter createCoverLetter(Long id) {


        AppUser user = AppUser.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();



        return CoverLetter.builder()
                .id(id)
                .name("Backend Developer Letter")
                .content("Cover letter content")
                .user(user)
                .build();
    }



    @Test
    void shouldReturnAllCoverLetters() {


        CoverLetter letter1 = createCoverLetter(1L);
        CoverLetter letter2 = createCoverLetter(2L);



        when(coverLetterRepository.findAll())
                .thenReturn(List.of(letter1, letter2));



        List<CoverLetterDTO> result =
                coverLetterService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "Backend Developer Letter",
                result.get(0).getName()
        );


        assertEquals(
                1L,
                result.get(0).getUserId()
        );


        verify(coverLetterRepository)
                .findAll();
    }



    @Test
    void shouldReturnCoverLetterById() {


        CoverLetter letter =
                createCoverLetter(1L);



        when(coverLetterRepository.findById(1L))
                .thenReturn(Optional.of(letter));



        CoverLetterDTO result =
                coverLetterService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "Backend Developer Letter",
                result.getName()
        );


        assertEquals(
                1L,
                result.getUserId()
        );


        verify(coverLetterRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenCoverLetterNotFound() {


        when(coverLetterRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> coverLetterService.findById(99L)
        );


        verify(coverLetterRepository)
                .findById(99L);
    }



    @Test
    void shouldReturnCoverLettersByUserId() {


        CoverLetter letter =
                createCoverLetter(1L);



        when(coverLetterRepository.findByUserId(1L))
                .thenReturn(List.of(letter));



        List<CoverLetterDTO> result =
                coverLetterService.findByUserId(1L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                1L,
                result.get(0).getUserId()
        );


        verify(coverLetterRepository)
                .findByUserId(1L);
    }



    @Test
    void shouldSaveCoverLetter() {


        CoverLetterDTO dto =
                CoverLetterDTO.builder()
                        .name("My Cover Letter")
                        .content("Text")
                        .userId(1L)
                        .build();



        CoverLetter savedLetter =
                createCoverLetter(10L);



        when(coverLetterRepository.save(any(CoverLetter.class)))
                .thenReturn(savedLetter);



        CoverLetterDTO result =
                coverLetterService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "Backend Developer Letter",
                result.getName()
        );


        verify(coverLetterRepository)
                .save(any(CoverLetter.class));
    }



    @Test
    void shouldDeleteCoverLetter() {


        coverLetterService.delete(1L);



        verify(coverLetterRepository)
                .deleteById(1L);
    }
}