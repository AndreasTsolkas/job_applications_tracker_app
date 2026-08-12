package com.example.jobtracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.DTO.LoginRequestDTO;
import com.example.jobtracker.DTO.LoginResponseDTO;
import com.example.jobtracker.DTO.RegisterRequestDTO;
import com.example.jobtracker.entity.AppUser;
import com.example.jobtracker.mapper.AppUserMapper;
import com.example.jobtracker.repository.AppUserRepository;
import com.example.jobtracker.security.JwtService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AppUserService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public List<AppUserDTO> getAllUsers() {

        return appUserRepository.findAll()
                .stream()
                .map(AppUserMapper::toDTO)
                .toList();
    }


    public AppUserDTO getById(Long id) {

        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return AppUserMapper.toDTO(user);
    }


    public AppUserDTO update(Long id, AppUserDTO dto) {

        AppUser user = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AppUserMapper.updateEntity(user, dto);

        AppUser updatedUser = appUserRepository.save(user);

        return AppUserMapper.toDTO(updatedUser);
    }


    public void delete(Long id) {

        appUserRepository.deleteById(id);
    }


    public AppUserDTO register(RegisterRequestDTO dto) {

        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new RuntimeException("Password is required");
        }

        if (appUserRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        LocalDateTime now = LocalDateTime.now();

        AppUser user = AppUser.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .passwordHash(passwordEncoder.encode(dto.getPassword()))
                .userRole("USER")
                .enabled(true)
                .createdAt(now)
                .updatedAt(now)
                .build();

        AppUser savedUser = appUserRepository.save(user);

        return AppUserMapper.toDTO(savedUser);
    }


    public LoginResponseDTO login(LoginRequestDTO dto) {

        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new RuntimeException("Invalid email or password");
        }

        AppUser user = appUserRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getId(),
                user.getEmail(),
                user.getUserRole()
        );

        return LoginResponseDTO.builder()
                .token(token)
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .build();
    }
}
