package com.melikash98.AdminSpring.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "Items")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id", unique = true, nullable = false)
    private String id;

    @Column(name = "name_item")
    private String name;

    @Column(name = "city_item")
    private String city;

    @Column(name = "country_item")
    private String country;

    @Column(name = "overview_item")
    private String overview;

    @Column(name = "photoCount_item", nullable = false)
    private int photoCount;

    @Column(name = "price_item")
    private String price;

    @Column(name = "type_items", nullable = false)
    private String typeItems;

    @Column(name = "free_delivery_item")
    private boolean freeDelivery;

    @Column(name = "discount_item", nullable = false)
    private String discount;


    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @Column(name = "isLiked_item")
    private boolean isLiked;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Photos> photos;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private ScoreItem scoreItem;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminUser adminUser;
}
