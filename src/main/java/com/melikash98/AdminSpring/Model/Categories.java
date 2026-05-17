package com.melikash98.AdminSpring.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "Categories")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id", unique = true, nullable = true)
    private String id;
    @Column(name = "name_category", nullable = false)
    private String name;
    @Column(name = "photo_item", nullable = false)
    private String photo;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminUser adminUser;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Items> items;
}
