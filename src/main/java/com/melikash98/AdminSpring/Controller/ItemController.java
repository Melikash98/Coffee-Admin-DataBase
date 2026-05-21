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
    public ResponseEntity<Items> addItem(@RequestBody ItemRequest dto) {
        return ResponseEntity.ok(itemService.addItem(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Items>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable String id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Items>> getItemsByAdmin(@PathVariable String adminId) {
        return ResponseEntity.ok(itemService.getItemsByAdmin(adminId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Items>> getItemsByCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(itemService.getItemsByCategory(categoryId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable String id, @RequestBody ItemRequest dto) {
        return ResponseEntity.ok(itemService.updateItem(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted successfully");
    }
}
