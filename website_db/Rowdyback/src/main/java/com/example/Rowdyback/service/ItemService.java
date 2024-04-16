package com.example.Rowdyback.services;

import com.example.Rowdyback.models.Item;
import com.example.Rowdyback.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long itemId, Item updatedItem) {
        return itemRepository.findById(itemId)
                .map(item -> {
                    item.setName(updatedItem.getName());
                    item.setDescription(updatedItem.getDescription());
                    item.setPrice(updatedItem.getPrice());
                    item.setQuantityAvailable(updatedItem.getQuantityAvailable());
                    item.setImageUrl(updatedItem.getImageUrl());
                    return itemRepository.save(item);
                })
                .orElseThrow(() -> new RuntimeException("Item not found!"));
    }

    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> findItemsByName(String name) {
        return itemRepository.findByNameContaining(name);
    }

    // TODO: query methods, e.g., by category, by price range, etc.
}
