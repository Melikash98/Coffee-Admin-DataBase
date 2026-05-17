package com.melikash98.AdminSpring.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String uid;
    private String token;
}
