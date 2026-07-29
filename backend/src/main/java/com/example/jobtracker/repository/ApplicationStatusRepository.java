package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.ApplicationStatus;

import java.util.Optional;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {

    Optional<ApplicationStatus> findByName(String name);
}
