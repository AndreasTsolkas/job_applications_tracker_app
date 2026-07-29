package com.example.demo.repository;

import com.example.demo.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    List<JobPosting> findByCompanyId(Long companyId);

    List<JobPosting> findByJobRoleId(Long jobRoleId);
}