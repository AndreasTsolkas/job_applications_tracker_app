package com.example.demo.repository;

import com.example.demo.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findByApplicationId(Long applicationId);

    List<Interview> findByTypeId(Long typeId);

    List<Interview> findByResultId(Long resultId);
}