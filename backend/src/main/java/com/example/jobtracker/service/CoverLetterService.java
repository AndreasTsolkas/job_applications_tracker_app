package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.CoverLetterDTO;
import com.example.jobtracker.entity.CoverLetter;
import com.example.jobtracker.mapper.CoverLetterMapper;
import com.example.jobtracker.repository.CoverLetterRepository;

import java.util.List;

@Service
public class CoverLetterService {

    private final CoverLetterRepository coverLetterRepository;

    public CoverLetterService(CoverLetterRepository coverLetterRepository) {
        this.coverLetterRepository = coverLetterRepository;
    }


    public List<CoverLetterDTO> findAll() {

        return coverLetterRepository.findAll()
                .stream()
                .map(CoverLetterMapper::toDTO)
                .toList();
    }


    public CoverLetterDTO findById(Long id) {

        CoverLetter coverLetter = coverLetterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cover letter not found"));

        return CoverLetterMapper.toDTO(coverLetter);
    }


    public List<CoverLetterDTO> findByUserId(Long userId) {

        return coverLetterRepository.findByUserId(userId)
                .stream()
                .map(CoverLetterMapper::toDTO)
                .toList();
    }


    public CoverLetterDTO save(CoverLetterDTO dto) {

        CoverLetter coverLetter = CoverLetterMapper.toEntity(dto);

        CoverLetter savedCoverLetter = coverLetterRepository.save(coverLetter);

        return CoverLetterMapper.toDTO(savedCoverLetter);
    }


    public CoverLetterDTO update(Long id, CoverLetterDTO dto) {

        CoverLetter coverLetter = coverLetterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cover letter not found"));

        CoverLetterMapper.updateEntity(coverLetter, dto);

        CoverLetter updatedCoverLetter = coverLetterRepository.save(coverLetter);

        return CoverLetterMapper.toDTO(updatedCoverLetter);
    }


    public void delete(Long id) {

        coverLetterRepository.deleteById(id);
    }
}