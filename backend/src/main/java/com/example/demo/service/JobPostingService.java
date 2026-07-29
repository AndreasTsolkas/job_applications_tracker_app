package com.example.demo.service;

import com.example.demo.entity.JobPosting;
import com.example.demo.repository.JobPostingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }


    public List<JobPosting> findAll() {
        return jobPostingRepository.findAll();
    }


    public JobPosting findById(Long id) {
        return jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));
    }


    public List<JobPosting> findByCompanyId(Long companyId) {
        return jobPostingRepository.findByCompanyId(companyId);
    }


    public List<JobPosting> findByJobRoleId(Long jobRoleId) {
        return jobPostingRepository.findByJobRoleId(jobRoleId);
    }


    public JobPosting save(JobPosting jobPosting) {
        return jobPostingRepository.save(jobPosting);
    }


    public void delete(Long id) {
        jobPostingRepository.deleteById(id);
    }
}