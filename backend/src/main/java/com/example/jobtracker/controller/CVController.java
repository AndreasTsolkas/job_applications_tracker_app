package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.CVDTO;
import com.example.jobtracker.service.CVService;

import java.util.List;

@RestController
@RequestMapping("/api/cvs")
public class CVController {

    private final CVService cvService;


    public CVController(CVService cvService) {
        this.cvService = cvService;
    }


    @GetMapping
    public ResponseEntity<List<CVDTO>> getAll() {

        return ResponseEntity.ok(
                cvService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CVDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                cvService.findById(id)
        );
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CVDTO>> getByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                cvService.findByUserId(userId)
        );
    }


    @PostMapping
    public ResponseEntity<CVDTO> create(
            @RequestBody CVDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cvService.save(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        cvService.delete(id);

        return ResponseEntity.noContent().build();
    }
}