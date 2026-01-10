package com.torresdev.inventory.error;

import java.time.OffsetDateTime;

public class ApiErrorResponse {

    private int status;
    private String error;
    private String message;
    private OffsetDateTime timestamp;

    public ApiErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = OffsetDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }
}
