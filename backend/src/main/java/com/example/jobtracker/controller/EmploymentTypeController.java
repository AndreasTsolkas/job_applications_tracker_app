package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.EmploymentTypeDTO;
import com.example.jobtracker.service.EmploymentTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/employment-types")
public class EmploymentTypeController {

    private final EmploymentTypeService employmentTypeService;


    public EmploymentTypeController(EmploymentTypeService employmentTypeService) {
        this.employmentTypeService = employmentTypeService;
    }


    @GetMapping
    public ResponseEntity<List<EmploymentTypeDTO>> getAll() {

        return ResponseEntity.ok(
                employmentTypeService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmploymentTypeDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employmentTypeService.findById(id)
        );
    }


    @PostMapping
    public ResponseEntity<EmploymentTypeDTO> create(
            @RequestBody EmploymentTypeDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employmentTypeService.save(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        employmentTypeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}