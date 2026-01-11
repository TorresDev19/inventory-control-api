package com.torresdev.inventory.config;

import com.torresdev.inventory.error.ApiErrorResponse;
import com.torresdev.inventory.exception.InsufficientStockException;
import com.torresdev.inventory.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================
    // PRODUTO NÃO ENCONTRADO
    // =========================
    @ExceptionHandler(ProductNotFoundException.class)
    public ApiErrorResponse handleProductNotFound(ProductNotFoundException ex) {
        return new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Product not found",
                ex.getMessage(),
                OffsetDateTime.now()
        );
    }

    // =========================
    // ESTOQUE INSUFICIENTE
    // =========================
    @ExceptionHandler(InsufficientStockException.class)
    public ApiErrorResponse handleInsufficientStock(InsufficientStockException ex) {
        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Insufficient stock",
                ex.getMessage(),
                OffsetDateTime.now()
        );
    }

    // =========================
    // ERRO GENÉRICO
    // =========================
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleGenericException(Exception ex) {
        return new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Unexpected internal server error",
                OffsetDateTime.now()
        );
    }
}
