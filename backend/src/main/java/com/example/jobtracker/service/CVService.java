package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.CV;
import com.example.jobtracker.repository.CVRepository;

import java.util.List;

@Service
public class CVService {

    private final CVRepository cvRepository;

    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }


    public List<CV> findAll() {
        return cvRepository.findAll();
    }


    public CV findById(Long id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CV not found"));
    }


    public List<CV> findByUserId(Long userId) {
        return cvRepository.findByUserId(userId);
    }


    public CV save(CV cv) {
        return cvRepository.save(cv);
    }


    public void delete(Long id) {
        cvRepository.deleteById(id);
    }
}