package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.Item;
import com.example.Rowdyback.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Items")
public class ItemController {

    private final ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item, @RequestParam Long userId) {
        Item newItem = itemService.addItem(item, userId);
        return ResponseEntity.ok(newItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.findItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false, defaultValue = "none") String sort) {
        List<Item> items;
        if ("asc".equalsIgnoreCase(sort)) {
            items = itemService.getAllItemsSorted(true);
        } else if ("desc".equalsIgnoreCase(sort)) {
            items = itemService.getAllItemsSorted(false);
        } else {
            items = itemService.findAllItems();
        }
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean isDeleted = itemService.deleteItem(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<List<Item>> getAllItemsSorted(boolean asc) {
        List<Item> items = asc ? itemService.getItemsSortedByPriceAsc() : itemService.getItemsSortedByPriceDesc();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchItems(@RequestParam(required = false) String query) {
        if (query == null || query.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Search query cannot be empty.");
        } else {
            List<Item> foundItems = itemService.searchItemsByString(query);
            if (foundItems.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: No items found matching your search criteria.");
            } else {
                return ResponseEntity.ok(foundItems);
            }
        }
    }
}
