package com.example.Rowdyback.controller;

public class AddItemRequest {
    private Long userId;
    private Long itemId;
    private int quantity;
    private int discountPercent;

    // Default constructor for JSON deserialization
    public AddItemRequest() {}

    // Getters
    public Long getUserId() {
        return userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    // Setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }
}
