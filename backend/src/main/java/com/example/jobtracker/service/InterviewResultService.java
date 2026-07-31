package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.InterviewResultDTO;
import com.example.jobtracker.entity.InterviewResult;
import com.example.jobtracker.mapper.InterviewResultMapper;
import com.example.jobtracker.repository.InterviewResultRepository;

import java.util.List;

@Service
public class InterviewResultService {

    private final InterviewResultRepository interviewResultRepository;

    public InterviewResultService(InterviewResultRepository interviewResultRepository) {
        this.interviewResultRepository = interviewResultRepository;
    }

    public List<InterviewResultDTO> findAll() {

        return interviewResultRepository.findAll()
                .stream()
                .map(InterviewResultMapper::toDTO)
                .toList();
    }

    public InterviewResultDTO findById(Long id) {

        InterviewResult result = interviewResultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview result not found"));

        return InterviewResultMapper.toDTO(result);
    }

    public InterviewResultDTO save(InterviewResultDTO dto) {

        InterviewResult result = InterviewResultMapper.toEntity(dto);

        InterviewResult savedResult = interviewResultRepository.save(result);

        return InterviewResultMapper.toDTO(savedResult);
    }

    public void delete(Long id) {

        interviewResultRepository.deleteById(id);
    }
}