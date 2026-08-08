package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.CompanyDTO;
import com.example.jobtracker.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll() {

        return ResponseEntity.ok(companyService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(companyService.findById(id));
    }


    @GetMapping("/sector/{sectorId}")
    public ResponseEntity<List<CompanyDTO>> getBySectorId(
            @PathVariable Long sectorId) {

        return ResponseEntity.ok(
                companyService.findBySectorId(sectorId)
        );
    }


    @PostMapping
    public ResponseEntity<CompanyDTO> create(
            @RequestBody CompanyDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(
            @PathVariable Long id,
            @RequestBody CompanyDTO dto) {

        return ResponseEntity.ok(
                companyService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        companyService.delete(id);

        return ResponseEntity.noContent().build();
    }
}