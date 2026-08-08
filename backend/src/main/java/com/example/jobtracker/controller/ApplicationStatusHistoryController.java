package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.ApplicationStatusHistoryDTO;
import com.example.jobtracker.service.ApplicationStatusHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/application-status-history")
public class ApplicationStatusHistoryController {

    private final ApplicationStatusHistoryService applicationStatusHistoryService;


    public ApplicationStatusHistoryController(
            ApplicationStatusHistoryService applicationStatusHistoryService) {

        this.applicationStatusHistoryService = applicationStatusHistoryService;
    }


    @GetMapping
    public ResponseEntity<List<ApplicationStatusHistoryDTO>> getAll() {

        return ResponseEntity.ok(
                applicationStatusHistoryService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApplicationStatusHistoryDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                applicationStatusHistoryService.findById(id)
        );
    }


    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<ApplicationStatusHistoryDTO>> getByApplicationId(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                applicationStatusHistoryService.findByApplicationId(applicationId)
        );
    }


    @PostMapping
    public ResponseEntity<ApplicationStatusHistoryDTO> create(
            @RequestBody ApplicationStatusHistoryDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(applicationStatusHistoryService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApplicationStatusHistoryDTO> update(
            @PathVariable Long id,
            @RequestBody ApplicationStatusHistoryDTO dto) {

        return ResponseEntity.ok(
                applicationStatusHistoryService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        applicationStatusHistoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}