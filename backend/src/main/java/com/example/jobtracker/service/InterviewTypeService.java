package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.InterviewTypeDTO;
import com.example.jobtracker.entity.InterviewType;
import com.example.jobtracker.mapper.InterviewTypeMapper;
import com.example.jobtracker.repository.InterviewTypeRepository;

import java.util.List;

@Service
public class InterviewTypeService {

    private final InterviewTypeRepository interviewTypeRepository;

    public InterviewTypeService(InterviewTypeRepository interviewTypeRepository) {
        this.interviewTypeRepository = interviewTypeRepository;
    }


    public List<InterviewTypeDTO> findAll() {

        return interviewTypeRepository.findAll()
                .stream()
                .map(InterviewTypeMapper::toDTO)
                .toList();
    }


    public InterviewTypeDTO findById(Long id) {

        InterviewType type = interviewTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview type not found"));

        return InterviewTypeMapper.toDTO(type);
    }


    public InterviewTypeDTO save(InterviewTypeDTO dto) {

        InterviewType type = InterviewTypeMapper.toEntity(dto);

        InterviewType savedType = interviewTypeRepository.save(type);

        return InterviewTypeMapper.toDTO(savedType);
    }


    public void delete(Long id) {

        interviewTypeRepository.deleteById(id);
    }
}