package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.JobRole;
import com.example.jobtracker.repository.JobRoleRepository;

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