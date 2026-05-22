package com.melikash98.AdminSpring.Service;

import com.melikash98.AdminSpring.DTO.ItemRequest;
import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Model.Items;
import com.melikash98.AdminSpring.Repository.AdminUserRepository;
import com.melikash98.AdminSpring.Repository.CategoryRepository;
import com.melikash98.AdminSpring.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final AdminUserRepository adminUserRepository;

    private String generateItemId() {
        String lastId = itemRepository.findLastId();

        if (lastId == null) {
            return "item1001";
        }

        try {
            int number = Integer.parseInt(lastId.replace("item", ""));
            return "item" + (number + 1);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid item ID format in database: " + lastId);
        }
    }

    @Transactional
    public Items addItem(ItemRequest request) {
        Categories category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + request.getCategoryId()));

        AdminUser admin = adminUserRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found: " + request.getAdminId()));

        if (itemRepository.existsByNameAndAdminUserUid(request.getName(), request.getAdminId())) {
            throw new RuntimeException("This Item has already been Added!");
        }
        Items items = Items.builder()
                .id(generateItemId())
                .name(request.getName())
                .photos(request.getPhotos())
                .overview(request.getOverview())
                .price(request.getPrice())
                .typeItems(request.getTypeItems())
                .location(request.getLocation())
                .discount(request.getDiscount())
                .photoCount(request.getPhotoCount())
                .category(category)
                .adminUser(admin)
                .deliveryPrice(request.getDeliveryPrice())
                .timestamp(request.getTimestamp()).build();
        Items saved = itemRepository.save(items);
        return saved;
    }
}