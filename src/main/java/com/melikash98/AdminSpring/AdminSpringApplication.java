package com.melikash98.AdminSpring;

import com.melikash98.AdminSpring.DTO.LoginRequest;
import com.melikash98.AdminSpring.DTO.RegisterRequest;
import com.melikash98.AdminSpring.Service.AuthService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AdminSpringApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(AdminSpringApplication.class, args);

        AuthService authService = context.getBean(AuthService.class);

    }

}
