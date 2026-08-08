package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.ApplicationDTO;
import com.example.jobtracker.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;


    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAll() {

        return ResponseEntity.ok(
                applicationService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                applicationService.findById(id)
        );
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationDTO>> getByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                applicationService.findByUserId(userId)
        );
    }


    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<ApplicationDTO>> getByStatusId(
            @PathVariable Long statusId) {

        return ResponseEntity.ok(
                applicationService.findByStatusId(statusId)
        );
    }


    @GetMapping("/job-posting/{jobPostingId}")
    public ResponseEntity<List<ApplicationDTO>> getByJobPostingId(
            @PathVariable Long jobPostingId) {

        return ResponseEntity.ok(
                applicationService.findByJobPostingId(jobPostingId)
        );
    }


    @PostMapping
    public ResponseEntity<ApplicationDTO> create(
            @RequestBody ApplicationDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(applicationService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApplicationDTO> update(
            @PathVariable Long id,
            @RequestBody ApplicationDTO dto) {

        return ResponseEntity.ok(
                applicationService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        applicationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}