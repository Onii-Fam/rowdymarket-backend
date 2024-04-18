package com.example.Rowdyback.repositories;

import com.example.Rowdyback.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    // In ShoppingCartRepository
    Optional<ShoppingCart> findByUserUserId(Long userId);


    // TODO: additional custom queries can be added here
}
