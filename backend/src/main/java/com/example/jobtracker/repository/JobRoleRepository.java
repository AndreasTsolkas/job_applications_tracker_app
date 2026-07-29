package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.JobRole;

import java.util.Optional;

public interface JobRoleRepository extends JpaRepository<JobRole, Long> {

    Optional<JobRole> findByName(String name);
}
