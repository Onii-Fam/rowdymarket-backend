package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.Item;
import com.example.Rowdyback.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    // Constructor injection instead of field injection
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item newItem = itemService.addItem(item);
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
}
