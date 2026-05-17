package com.melikash98.AdminSpring.DTO;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String userName;
}