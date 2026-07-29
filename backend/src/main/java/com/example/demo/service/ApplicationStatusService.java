package com.example.demo.service;

import com.example.demo.entity.ApplicationStatus;
import com.example.demo.repository.ApplicationStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationStatusService {

    private final ApplicationStatusRepository applicationStatusRepository;

    public ApplicationStatusService(ApplicationStatusRepository applicationStatusRepository) {
        this.applicationStatusRepository = applicationStatusRepository;
    }


    public List<ApplicationStatus> findAll() {
        return applicationStatusRepository.findAll();
    }


    public ApplicationStatus findById(Long id) {
        return applicationStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application status not found"));
    }


    public ApplicationStatus save(ApplicationStatus applicationStatus) {
        return applicationStatusRepository.save(applicationStatus);
    }


    public void delete(Long id) {
        applicationStatusRepository.deleteById(id);
    }
}