package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.JobRoleDTO;
import com.example.jobtracker.service.JobRoleService;

import java.util.List;

@RestController
@RequestMapping("/api/job-roles")
public class JobRoleController {

    private final JobRoleService jobRoleService;


    public JobRoleController(JobRoleService jobRoleService) {
        this.jobRoleService = jobRoleService;
    }


    @GetMapping
    public ResponseEntity<List<JobRoleDTO>> getAll() {

        return ResponseEntity.ok(jobRoleService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobRoleDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(jobRoleService.findById(id));
    }


    @PostMapping
    public ResponseEntity<JobRoleDTO> create(
            @RequestBody JobRoleDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobRoleService.save(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<JobRoleDTO> update(
            @PathVariable Long id,
            @RequestBody JobRoleDTO dto) {

        return ResponseEntity.ok(
                jobRoleService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        jobRoleService.delete(id);

        return ResponseEntity.noContent().build();
    }
}