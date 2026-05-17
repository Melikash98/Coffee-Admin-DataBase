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

    @Column(name = "name_item", nullable = false)
    private String name;

    @Column(name = "city_item", nullable = false)
    private String city;

    @Column(name = "country_item", nullable = false)
    private String country;

    @Column(name = "isLiked_item")
    private boolean isLiked;

    @Column(name = "overview_item", nullable = false)
    private String overview;

    @Column(name = "photoCount_item", nullable = false)
    private int photoCount;

    @Column(name = "price_item", nullable = false)
    private String price;

    @Column(name = "type_items", nullable = false)
    private String typeItems;

    @Column(name = "timestamp", nullable = false)
    private long timestamp;


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
