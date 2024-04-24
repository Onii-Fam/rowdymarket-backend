package com.example.Rowdyback.repositories;

import com.example.Rowdyback.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);


    List<Order> findByOrderStatus(String status);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    //TODO: additional queries



    @Query("SELECT o FROM Order o WHERE o.userId = :userId ORDER BY o.orderDate DESC")
    List<Order> findByUserIdDesc(Long userId);

    @Query("SELECT o FROM Order o WHERE o.userId = :userId ORDER BY o.orderDate ASC")
    List<Order> findByUserIdAsc(Long userId);


}

