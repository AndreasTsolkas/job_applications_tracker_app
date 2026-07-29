package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.EmploymentType;

import java.util.Optional;

public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {

    Optional<EmploymentType> findByName(String name);
}