package com.torresdev.inventory.dto;

import java.time.OffsetDateTime;

public class HealthResponseDTO {

    private String status;
    private String message;
    private OffsetDateTime timestamp;

    public HealthResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = OffsetDateTime.now();
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
