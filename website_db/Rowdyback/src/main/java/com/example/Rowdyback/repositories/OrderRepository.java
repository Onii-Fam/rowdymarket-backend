package com.example.Rowdyback.repositories;

import com.example.Rowdyback.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Correct method to find orders by user's ID
    List<Order> findByUserUserId(Long userId);  // Adjusted to match the entity mapping

    List<Order> findByOrderStatus(String status);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    //TODO: additional queries
}

