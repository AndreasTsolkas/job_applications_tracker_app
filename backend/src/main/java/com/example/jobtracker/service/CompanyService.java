package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.Company;
import com.example.jobtracker.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public List<Company> findAll() {
        return companyRepository.findAll();
    }


    public Company findById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }


    public List<Company> findBySectorId(Long sectorId) {
        return companyRepository.findBySectorId(sectorId);
    }


    public Company save(Company company) {
        return companyRepository.save(company);
    }


    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}