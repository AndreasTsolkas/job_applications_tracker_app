package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.InterviewDTO;
import com.example.jobtracker.entity.Interview;
import com.example.jobtracker.mapper.InterviewMapper;
import com.example.jobtracker.repository.InterviewRepository;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
    }


    public List<InterviewDTO> findAll() {

        return interviewRepository.findAll()
                .stream()
                .map(InterviewMapper::toDTO)
                .toList();
    }


    public InterviewDTO findById(Long id) {

        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        return InterviewMapper.toDTO(interview);
    }


    public List<InterviewDTO> findByApplicationId(Long applicationId) {

        return interviewRepository.findByApplicationId(applicationId)
                .stream()
                .map(InterviewMapper::toDTO)
                .toList();
    }


    public InterviewDTO save(InterviewDTO dto) {

        Interview interview = InterviewMapper.toEntity(dto);

        Interview savedInterview = interviewRepository.save(interview);

        return InterviewMapper.toDTO(savedInterview);
    }


    public InterviewDTO update(Long id, InterviewDTO dto) {

        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        InterviewMapper.updateEntity(interview, dto);

        Interview updatedInterview = interviewRepository.save(interview);

        return InterviewMapper.toDTO(updatedInterview);
    }


    public void delete(Long id) {

        interviewRepository.deleteById(id);
    }
}