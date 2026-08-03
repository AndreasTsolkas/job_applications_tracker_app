package com.example.jobtracker.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.jobtracker.DTO.ApplicationStatusHistoryDTO;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.entity.ApplicationStatus;
import com.example.jobtracker.entity.ApplicationStatusHistory;


class ApplicationStatusHistoryMapperTest {


    @Test
    void shouldMapEntityToDTO() {


        Application application = Application.builder()
                .id(10L)
                .build();


        ApplicationStatus status = ApplicationStatus.builder()
                .id(20L)
                .name("Interview")
                .build();


        ApplicationStatusHistory history =
                ApplicationStatusHistory.builder()
                        .id(1L)
                        .application(application)
                        .status(status)
                        .notes("Moved to interview")
                        .changedAt(LocalDateTime.now())
                        .build();



        ApplicationStatusHistoryDTO dto =
                ApplicationStatusHistoryMapper.toDTO(history);



        assertNotNull(dto);


        assertEquals(
                1L,
                dto.getId()
        );


        assertEquals(
                10L,
                dto.getApplicationId()
        );


        assertEquals(
                20L,
                dto.getStatusId()
        );


        assertEquals(
                "Moved to interview",
                dto.getNotes()
        );
    }



    @Test
    void shouldMapDTOToEntity() {


        ApplicationStatusHistoryDTO dto =
                ApplicationStatusHistoryDTO.builder()
                        .id(1L)
                        .applicationId(10L)
                        .statusId(20L)
                        .notes("Rejected")
                        .build();



        ApplicationStatusHistory history =
                ApplicationStatusHistoryMapper.toEntity(dto);



        assertNotNull(history);



        assertEquals(
                1L,
                history.getId()
        );


        assertNotNull(
                history.getApplication()
        );


        assertEquals(
                10L,
                history.getApplication().getId()
        );


        assertNotNull(
                history.getStatus()
        );


        assertEquals(
                20L,
                history.getStatus().getId()
        );


        assertEquals(
                "Rejected",
                history.getNotes()
        );
    }



    @Test
    void shouldHandleNullRelationsWhenMappingEntityToDTO() {


        ApplicationStatusHistory history =
                ApplicationStatusHistory.builder()
                        .id(1L)
                        .notes("No relations")
                        .build();



        ApplicationStatusHistoryDTO dto =
                ApplicationStatusHistoryMapper.toDTO(history);



        assertNotNull(dto);


        assertNull(
                dto.getApplicationId()
        );


        assertNull(
                dto.getStatusId()
        );
    }



    @Test
    void shouldReturnNullWhenEntityIsNull() {


        ApplicationStatusHistoryDTO dto =
                ApplicationStatusHistoryMapper.toDTO(null);


        assertNull(dto);
    }



    @Test
    void shouldReturnNullWhenDTOIsNull() {


        ApplicationStatusHistory history =
                ApplicationStatusHistoryMapper.toEntity(null);


        assertNull(history);
    }
}