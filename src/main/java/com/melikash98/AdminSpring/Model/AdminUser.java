package com.melikash98.AdminSpring.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {
    @Unique
    private String uid;
    @Unique
    private String email;
    @Unique
    private String userName;
    private String password;
    private String ownerName;
    private String shoopName;
    private String ownerPhone;
    private String ownerPhoto;
}

