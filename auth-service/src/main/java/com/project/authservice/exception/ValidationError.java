package com.project.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String fieldValue;
    private String message;

}
