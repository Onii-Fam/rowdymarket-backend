package com.example.Rowdyback.controller;

import com.example.Rowdyback.model.Order;
import com.example.Rowdyback.model.User;
import com.example.Rowdyback.repositories.UserRepository;
import com.example.Rowdyback.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;


import java.util.List;

@RestController
@RequestMapping("/api/Orders")
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @Autowired
    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @RequestParam Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        order.setUser(user);
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam(required = false) String sortBy) {
        Sort sort = Sort.unsorted();
        if (sortBy != null) {
            switch (sortBy.toLowerCase()) {
                case "price_asc":
                    sort = Sort.by("price").ascending();
                    break;
                case "price_desc":
                    sort = Sort.by("price").descending();
                    break;
                case "username":
                    sort = Sort.by("user.username"); // Assuming there is a 'username' field in a related User entity
                    break;
                case "date":
                    sort = Sort.by("orderDate").ascending();
                    break;
            }
        }
        List<Order> orders = orderService.findAllOrders(sort);
        return ResponseEntity.ok(orders);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok().build();
    }

    //TODO: Additional endpoints
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findOrdersByUserId(userId);
        if(orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }


}
