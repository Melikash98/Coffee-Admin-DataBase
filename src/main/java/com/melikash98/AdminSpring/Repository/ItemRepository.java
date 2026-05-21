package com.melikash98.AdminSpring.Repository;

import com.melikash98.AdminSpring.Model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Items, String> {
    List<Items> findByAdminUserUid(String adminId);

    List<Items> findByCategoryId(String categoryId);

    List<Items> findByTypeItems(String typeItems);

    boolean existsByNameAndAdminUserUid(String name, String adminId);
}
