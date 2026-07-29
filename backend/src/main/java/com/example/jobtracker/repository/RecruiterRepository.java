package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.Recruiter;

import java.util.List;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    List<Recruiter> findByCompanyId(Long companyId);
}
