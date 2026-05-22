package com.melikash98.AdminSpring.Repository;

import com.melikash98.AdminSpring.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Items, String> {

    @Query("SELECT i.id FROM Items i ORDER BY i.id DESC LIMIT 1")
    String findLastId();

    List<Items> findByAdminInfoAdminId(String adminId);

    List<Items> findByCategoryId(String categoryId);

    List<Items> findByTypeItems(String typeItems);

    boolean existsById(String id);

    boolean existsByCategoryId(String categoryId);

    boolean existsByNameAndAdminInfoAdminId(String name, String adminId);
}
