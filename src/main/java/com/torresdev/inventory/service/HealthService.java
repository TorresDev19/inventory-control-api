package com.torresdev.inventory.service;

import com.torresdev.inventory.dto.HealthResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public HealthResponseDTO check() {
        return new HealthResponseDTO(
                "UP",
                "Inventory Control API is running"
        );
    }
}