package com.melikash98.AdminSpring.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ItemRequest {
    private String name;
    private String overview;
    private String price;
    private String typeItems;
    private String discount;
    private String deliveryPrice;
    private boolean freeDelivery;
    private String categoryId;
    private String adminId;
    private List<String> photos;
    private boolean isLiked;
    private String city;
    private String country;
}
