package com.example.Rowdyback.services;

import com.example.Rowdyback.models.Order;
import com.example.Rowdyback.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    order.setOrderDate(updatedOrder.getOrderDate());
                    order.setTotalAmount(updatedOrder.getTotalAmount());
                    order.setTaxAmount(updatedOrder.getTaxAmount());
                    order.setDiscountCode(updatedOrder.getDiscountCode());
                    order.setOrderStatus(updatedOrder.getOrderStatus());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    public void cancelOrder(Long orderId) {
        orderRepository.findById(orderId)
                .map(order -> {
                    order.setOrderStatus("Cancelled");
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    package com.example.Rowdyback.services;

import com.example.Rowdyback.models.Order;
import com.example.Rowdyback.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
    public class OrderService {

        @Autowired
        private OrderRepository orderRepository;

        // Existing methods for creating, updating, and cancelling an order...

        public List<Order> findAllOrders() {
            return orderRepository.findAll();
        }

        public Optional<Order> findOrderById(Long orderId) {
            return orderRepository.findById(orderId);
        }

        public List<Order> findOrdersByUserId(Long userId) {
            return orderRepository.findByUserId(userId);
        }

        public List<Order> findOrdersByStatus(String status) {
            return orderRepository.findByOrderStatus(status);
        }

        // TODO: Other query methods, e.g., by date range, by total amount, etc.
    }

}
