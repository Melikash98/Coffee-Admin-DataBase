package com.melikash98.AdminSpring.Service;


import com.melikash98.AdminSpring.DTO.CategoryRequest;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Categories addCategory(CategoryRequest dto) {
        Categories category = Categories.builder()
                .name(dto.getName())
                .photo(dto.getPhoto())
                .build();
        return categoryRepository.save(category);
    }
}
