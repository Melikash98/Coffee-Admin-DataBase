package com.melikash98.AdminSpring.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ItemRequest {
    private String name;

    @JsonProperty("overView")
    private String overview;

    @JsonProperty("types")
    private String typeItems;

    private String price;
    private String discount;
    private String deliveryPrice;
    private boolean freeDelivery;
    private String categoryId;
    private String adminId;
    private String location;

    @JsonProperty("photos")
    private List<String> photos;

    private String city;
    private String country;
    private boolean isLiked;
}
