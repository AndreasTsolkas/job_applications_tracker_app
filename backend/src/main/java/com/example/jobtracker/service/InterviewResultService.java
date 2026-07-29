package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.InterviewResult;
import com.example.jobtracker.repository.InterviewResultRepository;

import java.util.List;

@Service
public class InterviewResultService {

    private final InterviewResultRepository interviewResultRepository;

    public InterviewResultService(InterviewResultRepository interviewResultRepository) {
        this.interviewResultRepository = interviewResultRepository;
    }


    public List<InterviewResult> findAll() {
        return interviewResultRepository.findAll();
    }


    public InterviewResult findById(Long id) {
        return interviewResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview result not found"));
    }


    public InterviewResult save(InterviewResult interviewResult) {
        return interviewResultRepository.save(interviewResult);
    }


    public void delete(Long id) {
        interviewResultRepository.deleteById(id);
    }
}