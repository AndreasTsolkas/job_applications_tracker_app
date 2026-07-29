package com.example.demo.repository;

import com.example.demo.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationStatusRepository extends JpaRepository<ApplicationStatus, Long> {

    Optional<ApplicationStatus> findByName(String name);
}
