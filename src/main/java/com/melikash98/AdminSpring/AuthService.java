package com.melikash98.AdminSpring;

import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.Model.LoginRequest;
import com.melikash98.AdminSpring.Model.RegisterRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthService {

    private AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public String register(RegisterRequest request) {

        if (adminUserRepository.existsByEmail(request.getEmail())) {
            return "This email has already been registered!";
        }

        if (adminUserRepository.existsByUserName(request.getUserName())) {
            return "This username is already taken!";
        }

        AdminUser admin = AdminUser.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(false)
                .build();

        adminUserRepository.save(admin);

        return "Successful registration!";
    }
    public String login(LoginRequest request) {

        AdminUser admin = adminUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Wrong email!"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            return "Wrong password!";
        }

        return "Login successful!";
    }
}
