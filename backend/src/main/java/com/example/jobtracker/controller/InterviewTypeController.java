package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.InterviewTypeDTO;
import com.example.jobtracker.service.InterviewTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/interview-types")
public class InterviewTypeController {

    private final InterviewTypeService interviewTypeService;


    public InterviewTypeController(
            InterviewTypeService interviewTypeService) {

        this.interviewTypeService = interviewTypeService;
    }


    @GetMapping
    public ResponseEntity<List<InterviewTypeDTO>> getAll() {

        return ResponseEntity.ok(
                interviewTypeService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<InterviewTypeDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                interviewTypeService.findById(id)
        );
    }


    @PostMapping
    public ResponseEntity<InterviewTypeDTO> create(
            @RequestBody InterviewTypeDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(interviewTypeService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<InterviewTypeDTO> update(
            @PathVariable Long id,
            @RequestBody InterviewTypeDTO dto) {

        return ResponseEntity.ok(
                interviewTypeService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        interviewTypeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}