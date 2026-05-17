package com.melikash98.AdminSpring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Photos")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;
}