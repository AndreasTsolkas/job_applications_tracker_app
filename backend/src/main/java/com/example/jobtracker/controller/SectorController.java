package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.SectorDTO;
import com.example.jobtracker.service.SectorService;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    private final SectorService sectorService;


    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }


    @GetMapping
    public ResponseEntity<List<SectorDTO>> getAll() {

        return ResponseEntity.ok(sectorService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<SectorDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(sectorService.findById(id));
    }


    @PostMapping
    public ResponseEntity<SectorDTO> create(
            @RequestBody SectorDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sectorService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SectorDTO> update(
            @PathVariable Long id,
            @RequestBody SectorDTO dto) {

        return ResponseEntity.ok(
                sectorService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        sectorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}