package com.melikash98.AdminSpring.Repository;

import com.melikash98.AdminSpring.Model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, String> {
    void deleteByItemId(String itemId);
}