package com.example.demo.repository;

import com.example.demo.entity.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {

    Optional<EmploymentType> findByName(String name);
}