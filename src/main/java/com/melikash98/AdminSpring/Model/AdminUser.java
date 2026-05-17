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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id", unique = true, nullable = false)
    private String uid;

    @Column(name = "email_admin", nullable = false, unique = true)
    private String email;

    @Column(name = "userName_admin", unique = true, nullable = false)
    private String userName;

    @Column(name = "password_admin")
    private String password;

    @Column(name = "name_admin")
    private String ownerName;

    @Column(name = "storyName_admin")
    private String shoopName;

    @Column(name = "phone_admin")
    private String ownerPhone;

    @Column(name = "photo_admin")
    private String ownerPhoto;

    @Column(name = "emailActive_admin")
    private boolean isActive;

    @OneToMany(mappedBy = "adminUser", cascade = CascadeType.ALL)
    private List<Categories> categories;

    @OneToMany(mappedBy = "adminUser", cascade = CascadeType.ALL)
    private List<Items> menuItems;
}
