package com.example.jobtracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.jobtracker.DTO.CVDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.entity.CV;
import com.example.jobtracker.repository.CVRepository;


class CVServiceTest {


    @Mock
    private CVRepository cvRepository;


    private CVService cvService;



    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        cvService =
                new CVService(cvRepository);
    }



    private CV createCV(Long id) {


        AppUser user = AppUser.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john@test.com")
                .build();



        return CV.builder()
                .id(id)
                .name("John CV")
                .filePath("/files/john-cv.pdf")
                .user(user)
                .build();
    }



    @Test
    void shouldReturnAllCVs() {


        CV cv1 = createCV(1L);
        CV cv2 = createCV(2L);



        when(cvRepository.findAll())
                .thenReturn(List.of(cv1, cv2));



        List<CVDTO> result =
                cvService.findAll();



        assertNotNull(result);


        assertEquals(
                2,
                result.size()
        );


        assertEquals(
                "John CV",
                result.get(0).getName()
        );


        assertEquals(
                1L,
                result.get(0).getUserId()
        );


        verify(cvRepository)
                .findAll();
    }



    @Test
    void shouldReturnCVById() {


        CV cv =
                createCV(1L);



        when(cvRepository.findById(1L))
                .thenReturn(Optional.of(cv));



        CVDTO result =
                cvService.findById(1L);



        assertNotNull(result);


        assertEquals(
                1L,
                result.getId()
        );


        assertEquals(
                "John CV",
                result.getName()
        );


        assertEquals(
                1L,
                result.getUserId()
        );


        verify(cvRepository)
                .findById(1L);
    }



    @Test
    void shouldThrowExceptionWhenCVNotFound() {


        when(cvRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                RuntimeException.class,
                () -> cvService.findById(99L)
        );


        verify(cvRepository)
                .findById(99L);
    }



    @Test
    void shouldReturnCVsByUserId() {


        CV cv =
                createCV(1L);



        when(cvRepository.findByUserId(1L))
                .thenReturn(List.of(cv));



        List<CVDTO> result =
                cvService.findByUserId(1L);



        assertNotNull(result);


        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                1L,
                result.get(0).getUserId()
        );


        verify(cvRepository)
                .findByUserId(1L);
    }



    @Test
    void shouldSaveCV() {


        CVDTO dto =
                CVDTO.builder()
                        .name("New CV")
                        .filePath("/files/new-cv.pdf")
                        .userId(1L)
                        .build();



        CV savedCV =
                createCV(10L);



        when(cvRepository.save(any(CV.class)))
                .thenReturn(savedCV);



        CVDTO result =
                cvService.save(dto);



        assertNotNull(result);


        assertEquals(
                10L,
                result.getId()
        );


        assertEquals(
                "John CV",
                result.getName()
        );


        verify(cvRepository)
                .save(any(CV.class));
    }



    @Test
    void shouldDeleteCV() {


        cvService.delete(1L);



        verify(cvRepository)
                .deleteById(1L);
    }
}