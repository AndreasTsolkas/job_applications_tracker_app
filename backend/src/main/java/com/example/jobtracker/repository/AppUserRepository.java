package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
