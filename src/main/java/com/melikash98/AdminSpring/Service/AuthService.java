package com.melikash98.AdminSpring.Service;

import com.melikash98.AdminSpring.DTO.UpdateProfileRequest;
import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.DTO.LoginRequest;
import com.melikash98.AdminSpring.DTO.RegisterRequest;
import com.melikash98.AdminSpring.Repository.AdminUserRepository;
import com.melikash98.AdminSpring.Security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AdminUserRepository adminUserRepository, JwtUtil jwtUtil) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    private String generateUid() {
        int number = (int) (Math.random() * 9000) + 1000;
        return "user" + number;
    }

    public String register(RegisterRequest request) {

        if (adminUserRepository.existsByEmail(request.getEmail())) {
            return "This email has already been registered!";
        }

        if (adminUserRepository.existsByUserName(request.getUserName())) {
            return "This username is already taken!";
        }

        AdminUser admin = AdminUser.builder()
                .uid(generateUid())
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

        return jwtUtil.generateToken(admin.getEmail());
    }

    public String updateInfo(String id, UpdateProfileRequest update) {
        AdminUser admin = adminUserRepository.findById(id).orElseThrow(() -> new RuntimeException("Your Account Not Found"));
        if (update.getOwnerName() != null) admin.setOwnerName(update.getOwnerName());
        if (update.getShoopName() != null) admin.setShoopName(update.getShoopName());
        if (update.getOwnerPhone() != null) admin.setOwnerPhone(update.getOwnerPhone());
        if (update.getOwnerGender() != null) admin.setOwnerGender(update.getOwnerGender());
        if (update.getOwnerBirthday() != null) admin.setOwnerBirthday(update.getOwnerBirthday());
        if (update.getUserName() != null) {
            if (adminUserRepository.existsByUserName(update.getUserName())) {
                return "This username is already taken!";
            }
            admin.setUserName(update.getUserName());
        }

        adminUserRepository.save(admin);
        return "Profile updated!";
    }
}
