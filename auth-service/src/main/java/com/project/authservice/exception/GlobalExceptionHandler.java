package com.project.authservice.exception;

import com.project.authservice.dto.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<GeneralResponse<List<ValidationError>>> handleValidationException(ValidationException ex) {
        GeneralResponse<List<ValidationError>> response = GeneralResponse.<List<ValidationError>>builder()
                .success(false)
                .message("Validation constraint violations occurred.")
                .data(ex.getErrors())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
