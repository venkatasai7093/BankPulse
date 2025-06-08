package com.bankpulse.authservice.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String role; // optional, default to ROLE_USER
}

