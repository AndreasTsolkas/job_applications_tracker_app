package com.example.demo.repository;

import com.example.demo.entity.InterviewResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewResultRepository extends JpaRepository<InterviewResult, Long> {

    Optional<InterviewResult> findByName(String name);
}
