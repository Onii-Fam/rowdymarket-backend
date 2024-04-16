
package com.example.Rowdyback.service;

import com.example.Rowdyback.model.Order;
import com.example.Rowdyback.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    // Methods for creating, updating, and cancelling an order...
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        // Include any initial setup logic for a new order here (e.g., setting initial status)
        order.setOrderStatus("Pending"); // Example default status
        return orderRepository.save(order);
    }

    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        return orderRepository.findById(orderId)
                .map(existingOrder -> {
                    // Copy properties from orderDetails to existingOrder, excluding ID
                    existingOrder.setOrderStatus(orderDetails.getOrderStatus());
                    // ... other properties as necessary
                    return orderRepository.save(existingOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setOrderStatus("Cancelled"); // Assuming you have a status field
        orderRepository.save(order);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findByOrderStatus(status);
    }

    // TODO: Other query methods, e.g., by date range, by total amount, etc.
}
