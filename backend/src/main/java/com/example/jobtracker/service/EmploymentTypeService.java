package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.EmploymentTypeDTO;
import com.example.jobtracker.entity.EmploymentType;
import com.example.jobtracker.mapper.EmploymentTypeMapper;
import com.example.jobtracker.repository.EmploymentTypeRepository;

import java.util.List;

@Service
public class EmploymentTypeService {

    private final EmploymentTypeRepository employmentTypeRepository;

    public EmploymentTypeService(EmploymentTypeRepository employmentTypeRepository) {
        this.employmentTypeRepository = employmentTypeRepository;
    }


    public List<EmploymentTypeDTO> findAll() {

        return employmentTypeRepository.findAll()
                .stream()
                .map(EmploymentTypeMapper::toDTO)
                .toList();
    }


    public EmploymentTypeDTO findById(Long id) {

        EmploymentType employmentType = employmentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employment type not found"));

        return EmploymentTypeMapper.toDTO(employmentType);
    }


    public EmploymentTypeDTO save(EmploymentTypeDTO dto) {

        EmploymentType employmentType = EmploymentTypeMapper.toEntity(dto);

        EmploymentType savedEmploymentType = employmentTypeRepository.save(employmentType);

        return EmploymentTypeMapper.toDTO(savedEmploymentType);
    }


    public EmploymentTypeDTO update(Long id, EmploymentTypeDTO dto) {

        EmploymentType employmentType = employmentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employment type not found"));

        EmploymentTypeMapper.updateEntity(employmentType, dto);

        EmploymentType updatedEmploymentType = employmentTypeRepository.save(employmentType);

        return EmploymentTypeMapper.toDTO(updatedEmploymentType);
    }


    public void delete(Long id) {

        employmentTypeRepository.deleteById(id);
    }
}