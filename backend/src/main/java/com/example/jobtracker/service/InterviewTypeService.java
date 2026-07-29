package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.InterviewType;
import com.example.jobtracker.repository.InterviewTypeRepository;

import java.util.List;

@Service
public class InterviewTypeService {

    private final InterviewTypeRepository interviewTypeRepository;

    public InterviewTypeService(InterviewTypeRepository interviewTypeRepository) {
        this.interviewTypeRepository = interviewTypeRepository;
    }


    public List<InterviewType> findAll() {
        return interviewTypeRepository.findAll();
    }


    public InterviewType findById(Long id) {
        return interviewTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview type not found"));
    }


    public InterviewType save(InterviewType interviewType) {
        return interviewTypeRepository.save(interviewType);
    }


    public void delete(Long id) {
        interviewTypeRepository.deleteById(id);
    }
}