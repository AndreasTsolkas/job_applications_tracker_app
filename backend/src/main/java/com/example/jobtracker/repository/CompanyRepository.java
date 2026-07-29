package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findBySectorId(Long sectorId);
}
