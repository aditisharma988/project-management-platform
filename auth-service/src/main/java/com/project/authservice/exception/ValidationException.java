package com.project.authservice.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<ValidationError> errors;
    private final Object additionalData;

    public ValidationException(List<ValidationError> errors) {
        super();
        this.errors = errors;
        this.additionalData = null;
    }

    public ValidationException(ValidationError error) {
        super();
        this.errors = List.of(error);
        this.additionalData = null;
    }

    public ValidationException(ValidationError error, Object additionalData) {
        super();
        this.errors = List.of(error);
        this.additionalData = additionalData;
    }
}