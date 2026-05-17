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

    @Column(name = "category_item", nullable = false)
    private String categoryName;

    @Column(name = "category_id_item", nullable = false)
    private String categoryId;

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

    @Column(name = "uidAdmin", nullable = false)
    private String uidAdmin;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Photos> photos;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private ScoreItem scoreItem;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminUser adminUser;

    @Override
    public String toString() {
        return "Items{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", isLiked=" + isLiked +
                ", overview='" + overview + '\'' +
                ", photoCount=" + photoCount +
                ", price='" + price + '\'' +
                ", typeItems='" + typeItems + '\'' +
                ", timestamp=" + timestamp +
                ", uidAdmin='" + uidAdmin + '\'' +
                ", photos=" + photos +
                ", scoreItem=" + scoreItem +
                ", adminUser=" + adminUser +
                '}';
    }
}
