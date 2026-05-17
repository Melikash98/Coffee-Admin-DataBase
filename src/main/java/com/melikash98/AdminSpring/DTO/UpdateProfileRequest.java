package com.melikash98.AdminSpring.DTO;


import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String ownerName;
    private String shoopName;
    private String ownerPhone;
    private String ownerGender;
    private String ownerBirthday;
    private String userName;
}
