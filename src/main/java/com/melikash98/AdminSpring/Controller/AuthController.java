package com.melikash98.AdminSpring.Controller;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);

            FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = decoded.getUid();
            AdminUser admin = firebaseService.getAdminByUid(uid);

            return ResponseEntity.ok(admin);

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token: " + e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        try {
            String uid = (String) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            AdminUser admin = firebaseService.getAdminByUid(uid);
            return ResponseEntity.ok(admin);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}