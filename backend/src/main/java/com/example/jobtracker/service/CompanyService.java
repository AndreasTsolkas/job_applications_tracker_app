package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.CompanyDTO;
import com.example.jobtracker.entity.Company;
import com.example.jobtracker.mapper.CompanyMapper;
import com.example.jobtracker.repository.CompanyRepository;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public List<CompanyDTO> findAll() {

        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::toDTO)
                .toList();
    }


    public CompanyDTO findById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return CompanyMapper.toDTO(company);
    }


    public List<CompanyDTO> findBySectorId(Long sectorId) {

        return companyRepository.findBySectorId(sectorId)
                .stream()
                .map(CompanyMapper::toDTO)
                .toList();
    }


    public CompanyDTO save(CompanyDTO dto) {

        Company company = CompanyMapper.toEntity(dto);

        Company savedCompany = companyRepository.save(company);

        return CompanyMapper.toDTO(savedCompany);
    }


    public void delete(Long id) {

        companyRepository.deleteById(id);
    }
}