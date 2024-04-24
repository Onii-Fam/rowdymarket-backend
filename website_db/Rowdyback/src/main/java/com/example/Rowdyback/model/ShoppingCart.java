package com.example.Rowdyback.model;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "ShoppingCarts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    private User user;

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyColumn(name = "item_id")
    @Column(name = "quantity")
    private Map<Long, Integer> items = new HashMap<>();  // This map stores item IDs and their quantities

    private Double totalAmount;
    private Double taxAmount;
    private String discountCode;

    public ShoppingCart() {}

    public ShoppingCart(User user, Double totalAmount, String discountCode) {
        this.user = user;
        this.totalAmount = totalAmount;
        this.taxAmount = 0.0825; // Tax rate could be set externally
        this.discountCode = discountCode;
    }

    public ShoppingCart(User user) {
        this.user = user;
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
    public Map<Long, Integer> getItems() { return items; }

    // Setters
    public void setCartId(Long cartId) { this.cartId = cartId; }
    public void setUser(User user) { this.user = user; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }
    public void setDiscountCode(String discountCode) { this.discountCode = discountCode; }
    public void setItems(Map<Long, Integer> items) { this.items = items; }

    // Methods to manage cart items
    public void addItem(Long itemId, int quantity) {
        items.put(itemId, items.getOrDefault(itemId, 0) + quantity);
    }

    public void removeItem(Long itemId) {
        if (items.containsKey(itemId)) {
            int newQuantity = items.get(itemId) - 1;
            if (newQuantity > 0) {
                items.put(itemId, newQuantity);
            } else {
                items.remove(itemId);
            }
        }
    }

    public void clearItems() {
        items.clear();
    }
}
