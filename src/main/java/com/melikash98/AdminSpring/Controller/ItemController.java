package com.melikash98.AdminSpring.Controller;

import com.melikash98.AdminSpring.DTO.ItemRequest;
import com.melikash98.AdminSpring.Model.Items;
import com.melikash98.AdminSpring.Service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Items> addItem(@RequestBody ItemRequest items) {
        return ResponseEntity.ok(itemService.addItem(items));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.getItemsById(id));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Items>> getItemsByCategoryId(@PathVariable String categoryId) {
        return ResponseEntity.ok(itemService.getItemsByCategoryId(categoryId));
    }
}
