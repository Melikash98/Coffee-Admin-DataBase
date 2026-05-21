package com.melikash98.AdminSpring.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ItemRequest {
    private String name;
    private String categoryId;
    private String categoryName;
    private String overview;
    private String typeItems;
    private String price;
    private int photoCount;
    private String discount;
    private String deliveryPrice;
    private String adminId;
    private String location;
    private List<String> photos;
    private long timestamp;
}
