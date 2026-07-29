package com.example.demo.service;

import com.example.demo.entity.InterviewResult;
import com.example.demo.repository.InterviewResultRepository;
import org.springframework.stereotype.Service;

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