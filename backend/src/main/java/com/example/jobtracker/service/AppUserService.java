package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.repository.AppUserRepository;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;


    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }
}