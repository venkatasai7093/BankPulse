package com.bankpulse.authservice.service;


import com.bankpulse.authservice.dto.AuthResponseDTO;
import com.bankpulse.authservice.dto.UserLoginDTO;
import com.bankpulse.authservice.dto.UserRegistrationDTO;
import com.bankpulse.authservice.dto.UserResponseDTO;
import com.bankpulse.authservice.model.User;
import com.bankpulse.authservice.repository.UserRepository;
import com.bankpulse.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserResponseDTO register(UserRegistrationDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole() != null ? dto.getRole() : "ROLE_USER")
                .build();

        User savedUser = userRepository.save(user);
        return mapToUserResponseDTO(savedUser);
    }

    public AuthResponseDTO authenticate(UserLoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return new AuthResponseDTO(token, mapToUserResponseDTO(user));
    }

    private UserResponseDTO mapToUserResponseDTO(User user) {
        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }
}
