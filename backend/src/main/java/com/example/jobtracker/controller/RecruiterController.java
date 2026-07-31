package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.RecruiterDTO;
import com.example.jobtracker.service.RecruiterService;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;


    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }


    @GetMapping
    public ResponseEntity<List<RecruiterDTO>> getAll() {

        return ResponseEntity.ok(recruiterService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<RecruiterDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(recruiterService.findById(id));
    }


    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<RecruiterDTO>> getByCompanyId(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                recruiterService.findByCompanyId(companyId)
        );
    }


    @PostMapping
    public ResponseEntity<RecruiterDTO> create(
            @RequestBody RecruiterDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recruiterService.save(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        recruiterService.delete(id);

        return ResponseEntity.noContent().build();
    }
}