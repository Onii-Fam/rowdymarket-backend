package com.example.Rowdyback.service;

import com.example.Rowdyback.model.Order;
import com.example.Rowdyback.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create a new order with initial setup logic
    @Transactional
    public Order createOrder(Order order) {
        if (order.getUser() == null) {
            throw new IllegalStateException("User must be set before creating an order");
        }
        order.setOrderStatus("Pending"); // Default status
        return orderRepository.save(order);
    }


    // Retrieve an order by ID
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Retrieve all orders
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findAllOrders(Sort sort) {
        return orderRepository.findAll(sort);
    }

    // Update an existing order by ID
    @Transactional
    public Order updateOrder(Long orderId, Order orderDetails) {
        return orderRepository.findById(orderId)
                .map(existingOrder -> {
                    existingOrder.setOrderStatus(orderDetails.getOrderStatus());
                    existingOrder.setTaxAmount(orderDetails.getTaxAmount());
                    existingOrder.setTotalAmount(orderDetails.getTotalAmount());
                    existingOrder.setOrderDate(orderDetails.getOrderDate());
                    existingOrder.setDiscountCode(orderDetails.getDiscountCode());
                    // Copy other properties as necessary
                    return orderRepository.save(existingOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    // Cancel an existing order
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setOrderStatus("Cancelled");
        orderRepository.save(order);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // Retrieve orders by status
    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findByOrderStatus(status);
    }

    // Retrieve orders within a date range
    public List<Order> findOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findBetweenDates(startDate, endDate);
    }
}

// TODO: Other query methods, e.g., by date range, by total amount, etc.

