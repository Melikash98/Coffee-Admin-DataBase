package com.melikash98.AdminSpring.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Table(name = "ScoreItem")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;

    @Column(name = "average", nullable = false)
    private double average;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "total", nullable = false)
    private double total;

    @ElementCollection
    @CollectionTable(name = "ratings", joinColumns = @JoinColumn(name = "score_id"))
    @MapKeyColumn(name = "user_id")
    @Column(name = "rating", nullable = false)
    private Map<String, Integer> ratings;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Items item;
}