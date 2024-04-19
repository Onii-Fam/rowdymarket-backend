package com.example.Rowdyback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")  // Explicitly defining the column name for clarity
    private User user;

    private LocalDateTime orderDate;
    private Double totalAmount;
    private Double taxAmount;
    private String discountCode;
    private String orderStatus;

public Order() {}

    public Order(User user, LocalDateTime orderDate, Double totalAmount, Double taxAmount, String discountCode, String orderStatus) {
        this.user = user;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.discountCode = discountCode;
        this.orderStatus = orderStatus;
    }

    // Getters
    public Long getOrderId() { return orderId; }
    public User getUser() { return user; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public Double getTotalAmount() { return totalAmount; }
    public Double getTaxAmount() { return taxAmount; }
    public String getDiscountCode() { return discountCode; }
    public String getOrderStatus() { return orderStatus; }

    // Setters
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public void setUser(User user) { this.user = user; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }
    public void setDiscountCode(String discountCode) { this.discountCode = discountCode; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}

