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
    @Column(name = "Id", unique = true, nullable = false)
    private String id;

    @Column(name = "name_item")
    private String name;

    @Column(name = "location_item")
    private String location;

    @Column(name = "overview_item")
    private String overview;

    @Column(name = "photoCount_item", nullable = false)
    private int photoCount;

    @Column(name = "price_item")
    private String price;

    @Column(name = "type_items", nullable = false)
    private String typeItems;

    @Column(name = "delivery_price_items", nullable = false)
    private String deliveryPrice;

    @Column(name = "discount_item", nullable = false)
    private String discount;


    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<String> photos;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private ScoreItem scoreItem;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminUser adminUser;
}
