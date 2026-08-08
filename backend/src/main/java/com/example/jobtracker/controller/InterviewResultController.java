package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.InterviewResultDTO;
import com.example.jobtracker.service.InterviewResultService;

import java.util.List;

@RestController
@RequestMapping("/api/interview-results")
public class InterviewResultController {

    private final InterviewResultService interviewResultService;


    public InterviewResultController(
            InterviewResultService interviewResultService) {

        this.interviewResultService = interviewResultService;
    }


    @GetMapping
    public ResponseEntity<List<InterviewResultDTO>> getAll() {

        return ResponseEntity.ok(
                interviewResultService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<InterviewResultDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                interviewResultService.findById(id)
        );
    }


    @PostMapping
    public ResponseEntity<InterviewResultDTO> create(
            @RequestBody InterviewResultDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(interviewResultService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<InterviewResultDTO> update(
            @PathVariable Long id,
            @RequestBody InterviewResultDTO dto) {

        return ResponseEntity.ok(
                interviewResultService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        interviewResultService.delete(id);

        return ResponseEntity.noContent().build();
    }
}