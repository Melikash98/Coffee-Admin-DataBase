package com.melikash98.AdminSpring.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
