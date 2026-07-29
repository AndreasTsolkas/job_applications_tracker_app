package com.example.demo.repository;

import com.example.demo.entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CVRepository extends JpaRepository<CV, Long> {

    List<CV> findByUserId(Long userId);
}
