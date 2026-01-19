package com.torresdev.inventory.dto.auth;

public record LoginResponseDTO(
        String token,
        String role
) {}
