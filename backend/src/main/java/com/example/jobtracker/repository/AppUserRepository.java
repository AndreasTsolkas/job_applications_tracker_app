package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
