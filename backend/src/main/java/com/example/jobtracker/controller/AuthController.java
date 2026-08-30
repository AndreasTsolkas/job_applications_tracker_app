package com.example.jobtracker.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.DTO.LoginRequestDTO;
import com.example.jobtracker.DTO.LoginResponseDTO;
import com.example.jobtracker.DTO.RegisterRequestDTO;
import com.example.jobtracker.service.AppUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserService appUserService;


    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/register")
    public ResponseEntity<AppUserDTO> register(
            @Valid @RequestBody RegisterRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(appUserService.register(dto));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        return ResponseEntity.ok(
                appUserService.login(dto)
        );
    }
}
