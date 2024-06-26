package com.example.Rowdyback.repositories;

import com.example.Rowdyback.model.Item;
import com.example.Rowdyback.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContaining(String name);

    @Query("SELECT i FROM Item i WHERE i.price < :maxPrice")
    List<Item> findByPriceLessThan(@Param("maxPrice") Double maxPrice);

    // Methods to find all items sorted by price
    List<Item> findAllByOrderByPriceAsc();

    List<Item> findAllByOrderByPriceDesc();

    // Using a custom query with sorting parameters
    @Query("SELECT i FROM Item i")
    List<Item> findAllItemsSorted(Sort sort);

    List<Item> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    List<Item> findByUser(User user); // Find items by user
    List<Item> findByUserUserId(Long userId);

}
