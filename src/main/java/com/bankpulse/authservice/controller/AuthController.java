package com.bankpulse.authservice.controller;


import com.bankpulse.authservice.dto.AuthResponseDTO;
import com.bankpulse.authservice.dto.UserLoginDTO;
import com.bankpulse.authservice.dto.UserRegistrationDTO;
import com.bankpulse.authservice.dto.UserResponseDTO;
import com.bankpulse.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationDTO dto) {
        UserResponseDTO user = authService.register(dto);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Authenticate user and get JWT token")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserLoginDTO dto) {
        AuthResponseDTO response = authService.authenticate(dto);
        return ResponseEntity.ok(response);
    }
}
