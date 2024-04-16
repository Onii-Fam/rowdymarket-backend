package com.example.Rowdyback.repositories;

import com.example.Rowdyback.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByNameContaining(String name);

    // custom query using JPQL for finding items below a certain price
    @Query("SELECT i FROM Item i WHERE i.price < :maxPrice")
    List<Item> findByPriceLessThan(Double maxPrice);

    // TODO: custom queries can be added here
}
