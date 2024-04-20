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
    @Column(name = "item_id")
    private Set<Long> itemIds = new HashSet<>();

    private Double totalAmount;
    private Double taxAmount;
    private String discountCode;

    private HashMap<Long, CartItem> shoppingCart;

    public ShoppingCart() {shoppingCart = new HashMap<>();}

    public ShoppingCart(User user, Double totalAmount, String discountCode) {
        this.user = user;
        this.totalAmount = totalAmount;
        this.taxAmount = 0.0825;
        this.discountCode = discountCode;
        shoppingCart = new HashMap<>();
    }

    public ShoppingCart(User user) {
        this.user = user;
        this.totalAmount = 1.0;
        this.taxAmount = 0.0;
        this.discountCode = "";
        shoppingCart = new HashMap<>();
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

    public void updateItemQuantity(Long itemId, int quantity) {
        CartItem c = shoppingCart.get(itemId);
        if(c != null) {
            int q = c.getQuantity();
            c.setQuantity(q + quantity);
            shoppingCart.put(itemId, c);
        }
    }
    public void addItem(Item item, int quantity, int discountPercent)
    {
        CartItem c = new CartItem(item,quantity,discountPercent);

        CartItem i = shoppingCart.get(item.getItemId());
        if(i == null) {
            shoppingCart.put(c.getItem().getItemId(), c);
        }
        else {
            updateItemQuantity(item.getItemId(), quantity);
        }
    }

    public void removeItem(Long itemId) {
        CartItem i = shoppingCart.get(itemId);
        if (i != null) {
            if (i.getQuantity() == 1) {
                shoppingCart.remove(itemId);
            } else {
                if (i.getQuantity() > 1) {
                    updateItemQuantity(itemId, -1);
                }
            }
        }
    }

    // Operations
    public void addItem(Item item) {
        this.addItem(item, 1, 0);
    }

    public void removeItem(Item item) {
        this.removeItem(item.getItemId());
    }

    public Map<Long, CartItem> getCartItems() {
        return shoppingCart;
    }
    public CartItem getCartItem(Long itemId) {
        return shoppingCart.get(itemId);
    }

    public void clearItems() {
        shoppingCart.clear();
    }
}
