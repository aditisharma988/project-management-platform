package com.project.authservice.service;

import com.project.authservice.dto.request.LoginRequest;
import com.project.authservice.dto.request.RegisterRequest;
import com.project.authservice.dto.response.UserResponse;

public interface AuthService  {

    UserResponse registerUser(RegisterRequest registerRequest);

    String loginUser(LoginRequest loginRequest);

}
