package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.ApplicationStatusDTO;
import com.example.jobtracker.entity.ApplicationStatus;
import com.example.jobtracker.mapper.ApplicationStatusMapper;
import com.example.jobtracker.repository.ApplicationStatusRepository;

import java.util.List;

@Service
public class ApplicationStatusService {

    private final ApplicationStatusRepository applicationStatusRepository;

    public ApplicationStatusService(ApplicationStatusRepository applicationStatusRepository) {
        this.applicationStatusRepository = applicationStatusRepository;
    }


    public List<ApplicationStatusDTO> findAll() {

        return applicationStatusRepository.findAll()
                .stream()
                .map(ApplicationStatusMapper::toDTO)
                .toList();
    }


    public ApplicationStatusDTO findById(Long id) {

        ApplicationStatus applicationStatus = applicationStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application status not found"));

        return ApplicationStatusMapper.toDTO(applicationStatus);
    }


    public ApplicationStatusDTO save(ApplicationStatusDTO dto) {

        ApplicationStatus applicationStatus = ApplicationStatusMapper.toEntity(dto);

        ApplicationStatus savedApplicationStatus =
                applicationStatusRepository.save(applicationStatus);

        return ApplicationStatusMapper.toDTO(savedApplicationStatus);
    }


    public void delete(Long id) {

        applicationStatusRepository.deleteById(id);
    }
}