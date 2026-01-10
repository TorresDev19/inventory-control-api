package com.torresdev.inventory.controller;

import com.torresdev.inventory.dto.HealthResponseDTO;
import com.torresdev.inventory.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/health")
    public HealthResponseDTO health() {
        return healthService.check();
    }
}