package com.torresdev.inventory.controller;

import com.torresdev.inventory.dto.auth.LoginRequestDTO;
import com.torresdev.inventory.dto.auth.LoginResponseDTO;
import com.torresdev.inventory.entity.Profile;
import com.torresdev.inventory.service.AuthService;
import com.torresdev.inventory.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request) {
        Profile profile = authService.login(
                request.getUsername(),
                request.getPassword());

        String token = jwtService.generateToken(profile);

        return ResponseEntity.ok(new LoginResponseDTO(token, profile.getRole()));
    }

}
