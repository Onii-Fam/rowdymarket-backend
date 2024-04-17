package com.example.Rowdyback.model;
import jakarta.persistence.*;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    @ManyToOne
    private User user;
    private Double totalAmount;
    private Double taxAmount;
    private String discountCode;

    public ShoppingCart() {}

    public ShoppingCart(User user, Double totalAmount, Double taxAmount, String discountCode) {
        this.user = user;
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.discountCode = discountCode;
    }

    public ShoppingCart(User user) {
        this.user = user;
        // Initialize the other fields with default values or as per business logic
        this.totalAmount = 0.0;
        this.taxAmount = 0.0;
        this.discountCode = "";
    }


    // Getters
    public Long getCartId() { return cartId; }
    public User getUser() { return user; }
    public Double getTotalAmount() { return totalAmount; }
    public Double getTaxAmount() { return taxAmount; }
    public String getDiscountCode() { return discountCode; }

    // Setters
    public void setCartId(Long cartId) { this.cartId = cartId; }
    public void setUser(User user) { this.user = user; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }
    public void setDiscountCode(String discountCode) { this.discountCode = discountCode; }
}

