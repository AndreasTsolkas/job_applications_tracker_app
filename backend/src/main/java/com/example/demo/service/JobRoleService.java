package com.example.demo.service;

import com.example.demo.entity.JobRole;
import com.example.demo.repository.JobRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobRoleService {

    private final JobRoleRepository jobRoleRepository;

    public JobRoleService(JobRoleRepository jobRoleRepository) {
        this.jobRoleRepository = jobRoleRepository;
    }


    public List<JobRole> findAll() {
        return jobRoleRepository.findAll();
    }


    public JobRole findById(Long id) {
        return jobRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job role not found"));
    }


    public JobRole save(JobRole jobRole) {
        return jobRoleRepository.save(jobRole);
    }


    public void delete(Long id) {
        jobRoleRepository.deleteById(id);
    }
}