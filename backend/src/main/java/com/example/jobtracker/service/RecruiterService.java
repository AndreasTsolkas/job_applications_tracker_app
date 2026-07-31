package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.RecruiterDTO;
import com.example.jobtracker.entity.Recruiter;
import com.example.jobtracker.mapper.RecruiterMapper;
import com.example.jobtracker.repository.RecruiterRepository;

import java.util.List;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    public List<RecruiterDTO> findAll() {

        return recruiterRepository.findAll()
                .stream()
                .map(RecruiterMapper::toDTO)
                .toList();
    }

    public RecruiterDTO findById(Long id) {

        Recruiter recruiter = recruiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        return RecruiterMapper.toDTO(recruiter);
    }

    public List<RecruiterDTO> findByCompanyId(Long companyId) {

        return recruiterRepository.findByCompanyId(companyId)
                .stream()
                .map(RecruiterMapper::toDTO)
                .toList();
    }

    public RecruiterDTO save(RecruiterDTO dto) {

        Recruiter recruiter = RecruiterMapper.toEntity(dto);

        Recruiter savedRecruiter = recruiterRepository.save(recruiter);

        return RecruiterMapper.toDTO(savedRecruiter);
    }

    public void delete(Long id) {

        recruiterRepository.deleteById(id);
    }
}