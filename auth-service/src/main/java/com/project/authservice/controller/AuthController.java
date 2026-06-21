package com.project.authservice.controller;

import com.project.authservice.dto.request.LoginRequest;
import com.project.authservice.dto.request.RegisterRequest;
import com.project.authservice.dto.response.GeneralResponse;
import com.project.authservice.dto.response.UserResponse;
import com.project.authservice.service.AuthService;
import com.project.authservice.utils.ApiResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Controller for authentication", description = "These are the APIs for Authentication")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //register
    @Operation(summary = "register a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "register user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to register user. Payload validation constraints failed.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server processing failure.", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<GeneralResponse<UserResponse>> registerUser(final @RequestBody RegisterRequest registerRequest) {
        return ApiResponseBody.created(authService.registerUser(registerRequest));
    }

    //login
    @Operation(summary = "login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "login user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Failed to authenticate. Invalid credentials or workspace mismatch.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server processing failure.", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<String>> loginUser(final @RequestBody LoginRequest loginRequest){
        return ApiResponseBody.ok(authService.loginUser(loginRequest));
        }


}
