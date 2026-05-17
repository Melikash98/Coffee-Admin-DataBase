package com.melikash98.AdminSpring.Controller;

import com.melikash98.AdminSpring.DTO.LoginRequest;
import com.melikash98.AdminSpring.DTO.RegisterRequest;
import com.melikash98.AdminSpring.DTO.UpdateProfileRequest;
import com.melikash98.AdminSpring.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @PutMapping("/update/{uid}")
    public ResponseEntity<String> updateProfile(
            @PathVariable String uid,
            @RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(authService.updateInfo(uid, request));
    }
}
