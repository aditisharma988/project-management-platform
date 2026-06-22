package com.project.authservice.dto.response;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard API response")
public class GeneralResponse<T> {

    @Schema(description = "Flag indicating operation success status")
    private boolean success;

    @Schema(description = "Descriptive feedback message")
    private String message;

    @Schema(description = "The target payload data")
    private T data;

    @Schema(description = "Timestamp of the operation execution")
    private LocalDateTime timestamp;
}


