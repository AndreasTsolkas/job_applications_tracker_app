package com.example.jobtracker.controller;

import org.springframework.web.bind.annotation.*;

import com.example.jobtracker.entity.AppUser;
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
    public List<AppUser> getUsers() {
        return appUserService.getAllUsers();
    }
}
