package com.example.jobtracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.DTO.AppUserDTO;
import com.example.jobtracker.service.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    private final AppUserService appUserService;


    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {

        return ResponseEntity.ok(
                appUserService.getAllUsers()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                appUserService.getById(id)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> update(
            @PathVariable Long id,
            @RequestBody AppUserDTO dto) {

        return ResponseEntity.ok(
                appUserService.update(id, dto)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        appUserService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
