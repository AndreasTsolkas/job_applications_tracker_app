package com.example.demo.repository;

import com.example.demo.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    List<Recruiter> findByCompanyId(Long companyId);
}
