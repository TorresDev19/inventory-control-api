package com.torresdev.inventory.config;

import com.torresdev.inventory.error.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.torresdev.inventory.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {

                ApiErrorResponse errorResponse = new ApiErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                "Unexpected internal server error");

                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {

                String message = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .findFirst()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .orElse("Validation error");

                ApiErrorResponse errorResponse = new ApiErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                message);

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<ApiErrorResponse> handleProductNotFound(ProductNotFoundException ex) {

                ApiErrorResponse errorResponse = new ApiErrorResponse(
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                ex.getMessage());

                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

}