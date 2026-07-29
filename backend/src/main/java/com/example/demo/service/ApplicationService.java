package com.example.demo.service;

import com.example.demo.entity.Application;
import com.example.demo.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    public List<Application> findAll() {
        return applicationRepository.findAll();
    }


    public Application findById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
    }


    public List<Application> findByUserId(Long userId) {
        return applicationRepository.findByUserId(userId);
    }


    public List<Application> findByStatusId(Long statusId) {
        return applicationRepository.findByStatusId(statusId);
    }


    public List<Application> findByJobPostingId(Long jobPostingId) {
        return applicationRepository.findByJobPostingId(jobPostingId);
    }


    public Application save(Application application) {
        return applicationRepository.save(application);
    }


    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}