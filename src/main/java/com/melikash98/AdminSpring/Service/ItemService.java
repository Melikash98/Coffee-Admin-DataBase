package com.melikash98.AdminSpring.Service;

import com.melikash98.AdminSpring.DTO.ItemRequest;
import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Model.Items;
import com.melikash98.AdminSpring.Repository.AdminUserRepository;
import com.melikash98.AdminSpring.Repository.CategoryRepository;
import com.melikash98.AdminSpring.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final AdminUserRepository adminUserRepository;

    public Items addItem(ItemRequest dto) {
        Categories category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        AdminUser admin = adminUserRepository.findById(dto.getAdminId()) // ✅
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Items item = Items.builder()
                .name(dto.getName())
                .city(dto.getCity())
                .country(dto.getCountry())
                .liked(dto.isLiked())
                .overview(dto.getOverview())
                .photoCount(dto.getPhotoCount())
                .price(dto.getPrice())
                .typeItems(dto.getTypeItems())
                .freeDelivery(dto.isFreeDelivery())
                .discount(dto.getDiscount())
                .timestamp(System.currentTimeMillis())
                .category(category)
                .adminUser(admin)
                .build();

        return itemRepository.save(item);
    }

    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    public Items getItemById(String id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public List<Items> getItemsByAdmin(String adminId) {
        return itemRepository.findByAdminUserUid(adminId);
    }

    public List<Items> getItemsByCategory(String categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    public Items updateItem(String id, ItemRequest dto) {
        Items item = getItemById(id);

        Categories category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        item.setName(dto.getName());
        item.setCity(dto.getCity());
        item.setCountry(dto.getCountry());
        item.setLiked(dto.isLiked());
        item.setOverview(dto.getOverview());
        item.setPhotoCount(dto.getPhotoCount());
        item.setPrice(dto.getPrice());
        item.setTypeItems(dto.getTypeItems());
        item.setFreeDelivery(dto.isFreeDelivery());
        item.setDiscount(dto.getDiscount());
        item.setCategory(category);

        return itemRepository.save(item);
    }

    public void deleteItem(String id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }
}