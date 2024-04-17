package com.example.Rowdyback.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    private User user;

    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "item_id")
    private Set<Long> itemIds = new HashSet<>();

    private Double totalAmount;
    private Double taxAmount;
    private String discountCode;

    public ShoppingCart() {}

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
    public Set<Long> getItemIds() { return itemIds; }

    // Setters
    public void setCartId(Long cartId) { this.cartId = cartId; }
    public void setUser(User user) { this.user = user; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public void setTaxAmount(Double taxAmount) { this.taxAmount = taxAmount; }
    public void setDiscountCode(String discountCode) { this.discountCode = discountCode; }
    public void setItemIds(Set<Long> itemIds) { this.itemIds = itemIds; }

    // Operations
    public void addItem(Item item) {
        itemIds.add(item.getItemId());
    }

    public void removeItem(Item item) {
        itemIds.remove(item.getItemId());
    }

    public void clearItems() {
        itemIds.clear();
    }
}
