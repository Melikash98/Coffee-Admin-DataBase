package com.melikash98.AdminSpring.Service;

import com.melikash98.AdminSpring.DTO.ItemRequest;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Model.Items;
import com.melikash98.AdminSpring.Model.ScoreItem;
import com.melikash98.AdminSpring.Model.StoreInfo;
import com.melikash98.AdminSpring.Repository.CategoryRepository;
import com.melikash98.AdminSpring.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

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

        String adminId = request.getStoreInfoRequest().getAdminId();
        if (itemRepository.existsByNameAndAdminInfoAdminId(request.getName(), adminId)) {
            throw new RuntimeException("This Item has already been Added!");
        }

        Items item = Items.builder()
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
                .deliveryPrice(request.getDeliveryPrice())
                .timestamp(request.getTimestamp())
                .build();

        ItemRequest.StoreInfoRequest storeReq = request.getStoreInfoRequest();
        StoreInfo storeInfo = StoreInfo.builder()
                .adminId(storeReq.getAdminId())
                .name(storeReq.getName())
                .storeName(storeReq.getStoreName())
                .phone(storeReq.getPhone())
                .email(storeReq.getEmail())
                .location(storeReq.getLocation())
                .photo(storeReq.getPhoto())
                .item(item)
                .build();

        ScoreItem scoreItem = ScoreItem.builder()
                .average(0)
                .count(0)
                .total(0)
                .ratings(new HashMap<>())
                .item(item)
                .build();

        item.setAdminInfo(storeInfo);
        item.setScore(scoreItem);


        return itemRepository.save(item);
    }
}