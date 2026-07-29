package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.Interview;
import com.example.jobtracker.repository.InterviewRepository;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }


    public List<Interview> findAll() {
        return interviewRepository.findAll();
    }


    public Interview findById(Long id) {
        return interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
    }


    public List<Interview> findByApplicationId(Long applicationId) {
        return interviewRepository.findByApplicationId(applicationId);
    }


    public Interview save(Interview interview) {
        return interviewRepository.save(interview);
    }


    public void delete(Long id) {
        interviewRepository.deleteById(id);
    }
}