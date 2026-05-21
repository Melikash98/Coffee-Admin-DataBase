package com.melikash98.AdminSpring.DTO;

import lombok.Data;

@Data
public class ItemRequest {
    private String name;
    private String city;
    private String country;
    private boolean isLiked;
    private String overview;
    private int photoCount;
    private String price;
    private String typeItems;
    private boolean freeDelivery;
    private int discount;
    private String categoryId;
    private String adminId;
}
