package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.ApplicationStatusDTO;
import com.example.jobtracker.service.ApplicationStatusService;

import java.util.List;

@RestController
@RequestMapping("/api/application-statuses")
public class ApplicationStatusController {

    private final ApplicationStatusService applicationStatusService;


    public ApplicationStatusController(
            ApplicationStatusService applicationStatusService) {

        this.applicationStatusService = applicationStatusService;
    }


    @GetMapping
    public ResponseEntity<List<ApplicationStatusDTO>> getAll() {

        return ResponseEntity.ok(
                applicationStatusService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApplicationStatusDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                applicationStatusService.findById(id)
        );
    }


    @PostMapping
    public ResponseEntity<ApplicationStatusDTO> create(
            @RequestBody ApplicationStatusDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(applicationStatusService.save(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        applicationStatusService.delete(id);

        return ResponseEntity.noContent().build();
    }
}