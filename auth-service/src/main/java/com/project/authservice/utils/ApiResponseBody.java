package com.project.authservice.utils;

import com.project.authservice.dto.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public final class ApiResponseBody {

    private ApiResponseBody() {}

    public static <T> ResponseEntity<GeneralResponse<T>> ok(final T data) {
        GeneralResponse<T> response = GeneralResponse.<T>builder()
                .success(true)
                .message("success")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> created(final T data) {
        GeneralResponse<T> response = GeneralResponse.<T>builder()
                .success(true)
                .message("resource created successfully")
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}