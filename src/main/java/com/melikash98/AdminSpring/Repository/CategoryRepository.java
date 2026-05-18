package com.melikash98.AdminSpring.Repository;

import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, String> {
    boolean existsByName(String name);
    @Query("SELECT c.id FROM Categories c ORDER BY c.id DESC LIMIT 1")
    String findLastId();
}
