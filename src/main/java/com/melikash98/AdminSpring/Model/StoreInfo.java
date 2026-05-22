package com.melikash98.AdminSpring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "store_info")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfo {
    @Id
    @Column(name = "item_id", nullable = false)
    private String id;

    @Column(name = "admin_id", nullable = false)
    private String adminId;

    @Column(name = "name")
    private String name;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "location")
    private String location;

    @Column(name = "photo", columnDefinition = "TEXT")
    private String photo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;
}
