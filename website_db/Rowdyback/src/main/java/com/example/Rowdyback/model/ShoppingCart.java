package com.example.Rowdyback.model;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

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

    private HashMap<Long, Item> shoppingCart;

    public ShoppingCart() {shoppingCart = new HashMap<>();}

    public ShoppingCart(User user, Double totalAmount, Double taxAmount, String discountCode) {
        this.user = user;
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.discountCode = discountCode;
        shoppingCart = new HashMap<>();
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

    public void addItem(Item item) {
        shoppingCart.put(item.getItemId(), item);
    }
    public void removeItem(Item item) {
        shoppingCart.remove(item.getItemId());
    }

}

