package com.example.Rowdyback.repositories;

import com.example.Rowdyback.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    // Assuming a user can have more than one shopping cart (e.g., wish lists),
    // otherwise you might use `Optional<ShoppingCart>`
    List<ShoppingCart> findByUserId(Long userId);

    // TODO: additional custom queries can be added here
}
