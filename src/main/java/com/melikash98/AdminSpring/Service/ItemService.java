package com.melikash98.AdminSpring.Service;

import com.melikash98.AdminSpring.DTO.ItemRequest;
import com.melikash98.AdminSpring.Model.AdminUser;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Model.Items;
import com.melikash98.AdminSpring.Model.Photos;
import com.melikash98.AdminSpring.Repository.AdminUserRepository;
import com.melikash98.AdminSpring.Repository.CategoryRepository;
import com.melikash98.AdminSpring.Repository.ItemRepository;
import com.melikash98.AdminSpring.Repository.PhotosRepository;
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
    private final PhotosRepository photosRepository;

    @Transactional
    public Items addItem(ItemRequest dto) {
        Categories category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryId()));

        AdminUser admin = adminUserRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found: " + dto.getAdminId()));

        Items item = Items.builder()
                .name(dto.getName())
                .city(dto.getCity())
                .country(dto.getCountry())
                .overview(dto.getOverview())
                .price(dto.getPrice())
                .typeItems(dto.getTypeItems())
                .discount(Integer.parseInt(dto.getDiscount()))
                .freeDelivery(dto.isFreeDelivery())
                .photoCount(dto.getPhotos() != null ? dto.getPhotos().size() : 0)
                .timestamp(System.currentTimeMillis())
                .isLiked(false)
                .category(category)
                .adminUser(admin)
                .build();

        Items saved = itemRepository.save(item);

        if (dto.getPhotos() != null && !dto.getPhotos().isEmpty()) {
            List<Photos> photoList = new ArrayList<>();
            for (int i = 0; i < dto.getPhotos().size(); i++) {
                photoList.add(Photos.builder()
                        .url(dto.getPhotos().get(i))
                        .orderIndex(i)
                        .item(saved)
                        .build());
            }
            photosRepository.saveAll(photoList);
        }

        return saved;
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

    @Transactional
    public Items updateItem(String id, ItemRequest dto) {
        Items item = getItemById(id);

        Categories category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryId()));

        item.setName(dto.getName());
        item.setCity(dto.getCity());
        item.setCountry(dto.getCountry());
        item.setLiked(dto.isLiked());
        item.setOverview(dto.getOverview());
        item.setPrice(dto.getPrice());
        item.setTypeItems(dto.getTypeItems());
        item.setFreeDelivery(dto.isFreeDelivery());
        item.setDiscount(Integer.parseInt(dto.getDiscount()));
        item.setCategory(category);

        if (dto.getPhotos() != null && !dto.getPhotos().isEmpty()) {
            photosRepository.deleteByItemId(id);
            List<Photos> photoList = new ArrayList<>();
            for (int i = 0; i < dto.getPhotos().size(); i++) {
                photoList.add(Photos.builder()
                        .url(dto.getPhotos().get(i))
                        .orderIndex(i)
                        .item(item)
                        .build());
            }
            photosRepository.saveAll(photoList);
            item.setPhotoCount(dto.getPhotos().size());
        }

        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(String id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }
}