package com.example.jobtracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.JobPostingDTO;
import com.example.jobtracker.service.JobPostingService;

import java.util.List;

@RestController
@RequestMapping("/api/job-postings")
public class JobPostingController {

    private final JobPostingService jobPostingService;


    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }


    @GetMapping
    public ResponseEntity<List<JobPostingDTO>> getAll() {

        return ResponseEntity.ok(
                jobPostingService.findAll()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                jobPostingService.findById(id)
        );
    }


    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobPostingDTO>> getByCompanyId(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                jobPostingService.findByCompanyId(companyId)
        );
    }


    @GetMapping("/job-role/{jobRoleId}")
    public ResponseEntity<List<JobPostingDTO>> getByJobRoleId(
            @PathVariable Long jobRoleId) {

        return ResponseEntity.ok(
                jobPostingService.findByJobRoleId(jobRoleId)
        );
    }


    @PostMapping
    public ResponseEntity<JobPostingDTO> create(
            @RequestBody JobPostingDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobPostingService.save(dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        jobPostingService.delete(id);

        return ResponseEntity.noContent().build();
    }
}