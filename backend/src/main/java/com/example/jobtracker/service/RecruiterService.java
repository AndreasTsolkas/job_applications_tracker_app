package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.Recruiter;
import com.example.jobtracker.repository.RecruiterRepository;

import java.util.List;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }


    public List<Recruiter> findAll() {
        return recruiterRepository.findAll();
    }


    public Recruiter findById(Long id) {
        return recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));
    }


    public List<Recruiter> findByCompanyId(Long companyId) {
        return recruiterRepository.findByCompanyId(companyId);
    }


    public Recruiter save(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }


    public void delete(Long id) {
        recruiterRepository.deleteById(id);
    }
}