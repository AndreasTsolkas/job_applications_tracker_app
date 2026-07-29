package com.example.demo.service;

import com.example.demo.entity.EmploymentType;
import com.example.demo.repository.EmploymentTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentTypeService {

    private final EmploymentTypeRepository employmentTypeRepository;

    public EmploymentTypeService(EmploymentTypeRepository employmentTypeRepository) {
        this.employmentTypeRepository = employmentTypeRepository;
    }


    public List<EmploymentType> findAll() {
        return employmentTypeRepository.findAll();
    }


    public EmploymentType findById(Long id) {
        return employmentTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employment type not found"));
    }


    public EmploymentType save(EmploymentType employmentType) {
        return employmentTypeRepository.save(employmentType);
    }


    public void delete(Long id) {
        employmentTypeRepository.deleteById(id);
    }
}