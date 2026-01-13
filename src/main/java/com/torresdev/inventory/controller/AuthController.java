package com.torresdev.inventory.controller;

import com.torresdev.inventory.dto.auth.LoginRequestDTO;
import com.torresdev.inventory.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDTO dto) {
        authService.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok().build();
    }
}
