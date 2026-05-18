package com.melikash98.AdminSpring.Controller;

import com.melikash98.AdminSpring.DTO.CategoryRequest;
import com.melikash98.AdminSpring.Model.Categories;
import com.melikash98.AdminSpring.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Categories> addCategory(@RequestBody CategoryRequest dto) {
        return ResponseEntity.ok(categoryService.addCategory(dto));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Categories>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }
}