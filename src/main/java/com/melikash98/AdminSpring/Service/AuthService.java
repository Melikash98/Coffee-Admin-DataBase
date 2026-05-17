package com.melikash98.AdminSpring.Service;

import com.melikash98.AdminSpring.DTO.PhotosAdmin;
import com.melikash98.AdminSpring.DTO.UpdateProfileRequest;
import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.DTO.LoginRequest;
import com.melikash98.AdminSpring.DTO.RegisterRequest;
import com.melikash98.AdminSpring.Model.LoginResponse;
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

    public LoginResponse register(RegisterRequest request) {
        if (adminUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("This email has already been registered!");
        }

        if (adminUserRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("This username is already taken!");
        }

        String uid = generateUid();

        AdminUser admin = AdminUser.builder()
                .uid(uid)
                .email(request.getEmail().toLowerCase())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .isActive(false)
                .build();

        adminUserRepository.save(admin);

        return new LoginResponse(uid, jwtUtil.generateToken(request.getEmail()));
    }

    public LoginResponse login(LoginRequest request) {
        AdminUser admin = adminUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Wrong email!"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Wrong password!");
        }

        return new LoginResponse(admin.getUid(), jwtUtil.generateToken(admin.getEmail()));
    }

    public String getUid(String email) {
        AdminUser admin = adminUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return admin.getUid();
    }

    public String updateInfo(String id, UpdateProfileRequest update) {
        AdminUser admin = adminUserRepository.findById(id).orElseThrow(() -> new RuntimeException("Your Account Not Found"));
        if (update.getOwnerName() != null) admin.setOwnerName(update.getOwnerName());
        if (update.getShoopName() != null) admin.setShoopName(update.getShoopName());
        if (update.getOwnerPhone() != null) admin.setOwnerPhone(update.getOwnerPhone());
        if (update.getOwnerGender() != null) admin.setOwnerGender(update.getOwnerGender());
        if (update.getOwnerBirthday() != null) admin.setOwnerBirthday(update.getOwnerBirthday());
        if (update.getOwnerLocation() != null) admin.setOwnerLocation(update.getOwnerLocation());
        if (update.getUserName() != null) {
            if (adminUserRepository.existsByUserName(update.getUserName())) {
                return "This username is already taken!";
            }
            admin.setUserName(update.getUserName());
        }

        adminUserRepository.save(admin);
        return "Profile updated!";
    }

    public String addProfilePhotos(String id, PhotosAdmin photos) {
        AdminUser userPhotos = adminUserRepository.findById(id).orElseThrow(() -> new RuntimeException("Your Account Not Found"));
        if (photos.getOwnerPhoto() != null) userPhotos.setOwnerPhoto(photos.getOwnerPhoto());
        if (photos.getOwnerBanner() != null) userPhotos.setOwnerBanner(photos.getOwnerBanner());

        adminUserRepository.save(userPhotos);


        return "Photos Add";
    }
}
