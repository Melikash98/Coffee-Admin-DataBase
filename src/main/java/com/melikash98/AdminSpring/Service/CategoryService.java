package com.melikash98.AdminSpring.Service;


import com.melikash98.AdminSpring.DTO.CategoryRequest;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private String generateCategoryId() {
        String lastId = categoryRepository.findLastId();

        if (lastId == null) {
            return "cat1001";
        }

        int number = Integer.parseInt(lastId.replace("cat", ""));
        return "cat" + (number + 1);
    }

    public Categories addCategory(CategoryRequest dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("This Category has already been Added!");
        }
        Categories category = Categories.builder()
                .id(generateCategoryId())
                .name(dto.getName())
                .photo(dto.getPhoto())
                .build();
        return categoryRepository.save(category);
    }
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Categories getCategoryById(String id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found!"));
    }
}
