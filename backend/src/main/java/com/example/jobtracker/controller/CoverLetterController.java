package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.CoverLetterDTO;
import com.example.jobtracker.service.CoverLetterService;

import java.util.List;

@RestController
@RequestMapping("/api/cover-letters")
public class CoverLetterController {

    private final CoverLetterService coverLetterService;


    public CoverLetterController(CoverLetterService coverLetterService) {
        this.coverLetterService = coverLetterService;
    }


    @GetMapping
    public ResponseEntity<List<CoverLetterDTO>> getAll() {

        return ResponseEntity.ok(
                coverLetterService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CoverLetterDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                coverLetterService.findById(id)
        );
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CoverLetterDTO>> getByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                coverLetterService.findByUserId(userId)
        );
    }


    @PostMapping
    public ResponseEntity<CoverLetterDTO> create(
            @RequestBody CoverLetterDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(coverLetterService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CoverLetterDTO> update(
            @PathVariable Long id,
            @RequestBody CoverLetterDTO dto) {

        return ResponseEntity.ok(
                coverLetterService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        coverLetterService.delete(id);

        return ResponseEntity.noContent().build();
    }
}