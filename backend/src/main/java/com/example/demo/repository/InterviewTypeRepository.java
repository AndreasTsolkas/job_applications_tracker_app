package com.example.demo.repository;

import com.example.demo.entity.InterviewType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewTypeRepository extends JpaRepository<InterviewType, Long> {

    Optional<InterviewType> findByName(String name);
}
