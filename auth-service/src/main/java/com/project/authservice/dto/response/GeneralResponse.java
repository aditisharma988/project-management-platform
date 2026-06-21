package com.project.authservice.dto.response;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard API response wrapper envelope")
public class GeneralResponse<T> {

    @Schema(description = "Flag indicating operation success status", example = "true")
    private boolean success;

    @Schema(description = "Descriptive feedback message", example = "Operation completed successfully.")
    private String message;

    @Schema(description = "The target core payload data")
    private T data;

    @Schema(description = "Timestamp instance when processing completed")
    private LocalDateTime timestamp;
}


