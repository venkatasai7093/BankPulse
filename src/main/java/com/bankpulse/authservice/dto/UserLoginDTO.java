package com.bankpulse.authservice.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String password;
}
