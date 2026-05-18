package com.melikash98.AdminSpring.Controller;

import com.melikash98.AdminSpring.DTO.CategoryRequest;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Categories> addCategory(@RequestBody CategoryRequest dto) {
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }
}