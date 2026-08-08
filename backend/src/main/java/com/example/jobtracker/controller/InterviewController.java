package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.InterviewDTO;
import com.example.jobtracker.service.InterviewService;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;


    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }


    @GetMapping
    public ResponseEntity<List<InterviewDTO>> getAll() {

        return ResponseEntity.ok(
                interviewService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<InterviewDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                interviewService.findById(id)
        );
    }


    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<InterviewDTO>> getByApplicationId(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(
                interviewService.findByApplicationId(applicationId)
        );
    }


    @PostMapping
    public ResponseEntity<InterviewDTO> create(
            @RequestBody InterviewDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(interviewService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<InterviewDTO> update(
            @PathVariable Long id,
            @RequestBody InterviewDTO dto) {

        return ResponseEntity.ok(
                interviewService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        interviewService.delete(id);

        return ResponseEntity.noContent().build();
    }
}