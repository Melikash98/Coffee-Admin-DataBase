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
    @Column(name = "Id", unique = true, nullable = true)
    private String uid;
    @Column(name = "email_admin", nullable = true)
    private String email;
    @Column(name = "userName_admin", unique = true, nullable = true)
    private String userName;
    @Column(name = "password_admin", nullable = true)
    private String password;
    @Column(name = "name_admin", nullable = true)
    private String ownerName;
    @Column(name = "storyName_admin", unique = true, nullable = true)
    private String shoopName;
    @Column(name = "phone_admin", nullable = true)
    private String ownerPhone;
    @Column(name = "photo_admin", nullable = true)
    private String ownerPhoto;
    @Column(name = "emailActive_admin", nullable = true)
    private boolean isActive;
    @OneToMany(mappedBy = "adminUser", cascade = CascadeType.ALL)
    private List<Items> menuItems;

    @Override
    public String toString() {
        return "AdminUser{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", shoopName='" + shoopName + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", ownerPhoto='" + ownerPhoto + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}

