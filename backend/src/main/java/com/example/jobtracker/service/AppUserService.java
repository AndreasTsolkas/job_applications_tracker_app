package com.example.jobtracker.service;

import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.mapper.AppUserMapper;
import com.example.jobtracker.repository.AppUserRepository;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;


    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    public List<AppUserDTO> getAllUsers() {

        return appUserRepository.findAll()
                .stream()
                .map(AppUserMapper::toDTO)
                .toList();
    }
}