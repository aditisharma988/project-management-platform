package com.project.authservice.service.impl;

import com.project.authservice.dto.request.LoginRequest;
import com.project.authservice.dto.request.RegisterRequest;
import com.project.authservice.dto.response.UserResponse;
import com.project.authservice.entity.User;
import com.project.authservice.exception.ErrorConstants;
import com.project.authservice.exception.ValidationException;
import com.project.authservice.repository.UserRepository;
import com.project.authservice.security.JwtUtils;
import com.project.authservice.service.AuthService;
import com.project.authservice.utils.ErrorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.ObjectUtils;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final ErrorUtils errorUtils;

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        if (ObjectUtils.isNotEmpty(request) &&
                userRepository.existsByUsernameAndTenantId(request.getUsername(), request.getTenantId())) {

            throw new ValidationException(errorUtils.getError(
                    "username",
                    request.getUsername(),
                    ErrorConstants.USERNAME_ALREADY_TAKEN
            ));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setTenantId(request.getTenantId());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setTenantId(savedUser.getTenantId());
        response.setRole(savedUser.getRole());
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;
    }

    @Override
    public String loginUser(LoginRequest request) {
        User user = userRepository.findByUsernameAndTenantId(request.getUsername(), request.getTenantId())
                .orElseThrow(() -> new ValidationException(errorUtils.getError(
                        "credentials",
                        null,
                        ErrorConstants.INVALID_CREDENTIALS
                )));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ValidationException(errorUtils.getError(
                    "password",
                    request.getPassword(),
                    ErrorConstants.INVALID_CREDENTIALS
            ));
        }

        return jwtUtils.generateToken(user.getUsername(), user.getTenantId(), user.getRole());
    }
}