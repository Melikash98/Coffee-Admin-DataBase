package com.melikash98.AdminSpring.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "Id", unique = true, nullable = false)
    private String id;

    @Column(name = "name_category", nullable = false)
    private String name;

    @Column(name = "photo_category", nullable = false)
    private String photo;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Items> items;
}