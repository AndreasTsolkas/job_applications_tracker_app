package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.ApplicationDTO;
import com.example.jobtracker.entity.Application;
import com.example.jobtracker.mapper.ApplicationMapper;
import com.example.jobtracker.repository.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    public List<ApplicationDTO> findAll() {

        return applicationRepository.findAll()
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public ApplicationDTO findById(Long id) {

        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return ApplicationMapper.toDTO(application);
    }


    public List<ApplicationDTO> findByUserId(Long userId) {

        return applicationRepository.findByUserId(userId)
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public List<ApplicationDTO> findByStatusId(Long statusId) {

        return applicationRepository.findByStatusId(statusId)
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public List<ApplicationDTO> findByJobPostingId(Long jobPostingId) {

        return applicationRepository.findByJobPostingId(jobPostingId)
                .stream()
                .map(ApplicationMapper::toDTO)
                .toList();
    }


    public ApplicationDTO save(ApplicationDTO dto) {

        Application application = ApplicationMapper.toEntity(dto);

        Application savedApplication = applicationRepository.save(application);

        return ApplicationMapper.toDTO(savedApplication);
    }


    public void delete(Long id) {

        applicationRepository.deleteById(id);
    }
}