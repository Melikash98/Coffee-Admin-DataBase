package com.melikash98.AdminSpring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "InfoAdmin")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    @Id
    @Column(name = "Id", unique = true, nullable = false)
    private String uid;

    @Column(name = "email_admin", nullable = false, unique = true)
    private String email;

    @Column(name = "user_name_admin", unique = true, nullable = false)
    private String userName;

    @Column(name = "password_admin")
    private String password;

    @Column(name = "name_admin")
    private String ownerName;

    @Column(name = "story_name_admin")
    private String shoopName;

    @Column(name = "phone_admin")
    private String ownerPhone;

    @Column(name = "photo_admin")
    private String ownerPhoto;

    @Column(name = "photo_banner_admin")
    private String ownerBanner;

    @Column(name = "gender_admin")
    private String ownerGender;

    @Column(name = "birthday_admin")
    private String ownerBirthday;

    @Column(name = "location_admin")
    private String ownerLocation;

    @Column(name = "email_active_admin")
    private boolean isActive;

}
