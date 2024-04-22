package com.example.Rowdyback.service;

import com.example.Rowdyback.model.Item;
import com.example.Rowdyback.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> findItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> updateItem(Long id, Item item) {
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(item.getName());
                    existingItem.setDescription(item.getDescription());
                    existingItem.setPrice(item.getPrice());
                    existingItem.setQuantityAvailable(item.getQuantityAvailable());
                    existingItem.setImageUrl(item.getImageUrl());
                    return itemRepository.save(existingItem);
                });
    }

    public boolean deleteItem(Long itemId) {
        if (itemRepository.existsById(itemId)) {
            itemRepository.deleteById(itemId);
            return true;
        }
        return false;
    }

    public List<Item> getAllItemsSorted(boolean asc) {
        Sort sort = asc ? Sort.by("price").ascending() : Sort.by("price").descending();
        return itemRepository.findAll(sort);
    }

    public List<Item> getItemsSortedByPriceAsc() {
        return itemRepository.findAll(Sort.by("price").ascending());
    }

    public List<Item> getItemsSortedByPriceDesc() {
        return itemRepository.findAll(Sort.by("price").descending());
    }

    public List<Item> searchItemsByString(String search) {
        return itemRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(search, search);
    }
}


