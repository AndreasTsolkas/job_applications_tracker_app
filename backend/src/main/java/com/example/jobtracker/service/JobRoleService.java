package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.JobRoleDTO;
import com.example.jobtracker.entity.JobRole;
import com.example.jobtracker.mapper.JobRoleMapper;
import com.example.jobtracker.repository.JobRoleRepository;

import java.util.List;

@Service
public class JobRoleService {

    private final JobRoleRepository jobRoleRepository;

    public JobRoleService(JobRoleRepository jobRoleRepository) {
        this.jobRoleRepository = jobRoleRepository;
    }


    public List<JobRoleDTO> findAll() {

        return jobRoleRepository.findAll()
                .stream()
                .map(JobRoleMapper::toDTO)
                .toList();
    }


    public JobRoleDTO findById(Long id) {

        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job role not found"));

        return JobRoleMapper.toDTO(jobRole);
    }


    public JobRoleDTO save(JobRoleDTO dto) {

        JobRole jobRole = JobRoleMapper.toEntity(dto);

        JobRole savedJobRole = jobRoleRepository.save(jobRole);

        return JobRoleMapper.toDTO(savedJobRole);
    }


    public JobRoleDTO update(Long id, JobRoleDTO dto) {

        JobRole jobRole = jobRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job role not found"));

        JobRoleMapper.updateEntity(jobRole, dto);

        JobRole updatedJobRole = jobRoleRepository.save(jobRole);

        return JobRoleMapper.toDTO(updatedJobRole);
    }


    public void delete(Long id) {

        jobRoleRepository.deleteById(id);
    }
}