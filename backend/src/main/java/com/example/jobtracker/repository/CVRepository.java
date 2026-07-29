package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.CV;

import java.util.List;

public interface CVRepository extends JpaRepository<CV, Long> {

    List<CV> findByUserId(Long userId);
}
