package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.JobPostingDTO;
import com.example.jobtracker.entity.JobPosting;
import com.example.jobtracker.mapper.JobPostingMapper;
import com.example.jobtracker.repository.JobPostingRepository;

import java.util.List;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }


    public List<JobPostingDTO> findAll() {

        return jobPostingRepository.findAll()
                .stream()
                .map(JobPostingMapper::toDTO)
                .toList();
    }


    public JobPostingDTO findById(Long id) {

        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));

        return JobPostingMapper.toDTO(jobPosting);
    }


    public List<JobPostingDTO> findByCompanyId(Long companyId) {

        return jobPostingRepository.findByCompanyId(companyId)
                .stream()
                .map(JobPostingMapper::toDTO)
                .toList();
    }


    public List<JobPostingDTO> findByJobRoleId(Long jobRoleId) {

        return jobPostingRepository.findByJobRoleId(jobRoleId)
                .stream()
                .map(JobPostingMapper::toDTO)
                .toList();
    }


    public JobPostingDTO save(JobPostingDTO dto) {

        JobPosting jobPosting = JobPostingMapper.toEntity(dto);

        JobPosting savedJobPosting = jobPostingRepository.save(jobPosting);

        return JobPostingMapper.toDTO(savedJobPosting);
    }


    public JobPostingDTO update(Long id, JobPostingDTO dto) {

        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job posting not found"));

        JobPostingMapper.updateEntity(jobPosting, dto);

        JobPosting updatedJobPosting = jobPostingRepository.save(jobPosting);

        return JobPostingMapper.toDTO(updatedJobPosting);
    }


    public void delete(Long id) {

        jobPostingRepository.deleteById(id);
    }
}