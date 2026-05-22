package com.melikash98.AdminSpring.Repository;

import com.melikash98.AdminSpring.Model.ScoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreItemRepository extends JpaRepository<ScoreItem, String> {
    Optional<ScoreItem> findByItemId(String itemId);
}
