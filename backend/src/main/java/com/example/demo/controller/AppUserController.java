package com.example.demo.controller;

import com.example.demo.entity.AppUser;
import com.example.demo.service.AppUserService;
import org.springframework.web.bind.annotation.*;

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
