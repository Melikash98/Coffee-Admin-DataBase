package com.melikash98.AdminSpring.Repository;

import com.melikash98.AdminSpring.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, String> {
}
