package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.ApplicationStatusHistory;
import com.example.jobtracker.repository.ApplicationStatusHistoryRepository;

import java.util.List;

@Service
public class ApplicationStatusHistoryService {

    private final ApplicationStatusHistoryRepository applicationStatusHistoryRepository;

    public ApplicationStatusHistoryService(ApplicationStatusHistoryRepository applicationStatusHistoryRepository) {
        this.applicationStatusHistoryRepository = applicationStatusHistoryRepository;
    }


    public List<ApplicationStatusHistory> findAll() {
        return applicationStatusHistoryRepository.findAll();
    }


    public ApplicationStatusHistory findById(Long id) {
        return applicationStatusHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application status history not found"));
    }


    public List<ApplicationStatusHistory> findByApplicationId(Long applicationId) {
        return applicationStatusHistoryRepository.findByApplicationId(applicationId);
    }


    public ApplicationStatusHistory save(ApplicationStatusHistory history) {
        return applicationStatusHistoryRepository.save(history);
    }


    public void delete(Long id) {
        applicationStatusHistoryRepository.deleteById(id);
    }
}