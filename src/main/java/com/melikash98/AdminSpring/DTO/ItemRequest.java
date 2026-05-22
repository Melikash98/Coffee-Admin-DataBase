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
    private String location;
    private List<String> photos;
    private long timestamp;

    private StoreInfoRequest storeInfoRequest;

    @Data
    public static class StoreInfoRequest {
        private String adminId;
        private String name;
        private String storeName;
        private String phone;
        private String email;
        private String location;
        private String photo;
    }
}
