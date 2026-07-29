package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.InterviewType;

import java.util.Optional;

public interface InterviewTypeRepository extends JpaRepository<InterviewType, Long> {

    Optional<InterviewType> findByName(String name);
}
