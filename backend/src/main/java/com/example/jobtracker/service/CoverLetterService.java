package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.CoverLetter;
import com.example.jobtracker.repository.CoverLetterRepository;

import java.util.List;

@Service
public class CoverLetterService {

    private final CoverLetterRepository coverLetterRepository;

    public CoverLetterService(CoverLetterRepository coverLetterRepository) {
        this.coverLetterRepository = coverLetterRepository;
    }


    public List<CoverLetter> findAll() {
        return coverLetterRepository.findAll();
    }


    public CoverLetter findById(Long id) {
        return coverLetterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cover letter not found"));
    }


    public List<CoverLetter> findByUserId(Long userId) {
        return coverLetterRepository.findByUserId(userId);
    }


    public CoverLetter save(CoverLetter coverLetter) {
        return coverLetterRepository.save(coverLetter);
    }


    public void delete(Long id) {
        coverLetterRepository.deleteById(id);
    }
}