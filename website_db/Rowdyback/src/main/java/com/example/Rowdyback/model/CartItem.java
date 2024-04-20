package com.example.Rowdyback.model;
public class CartItem {
    private Item item;
    private int quantity;

    private int discountPercent = 0;

    public CartItem(Item item, int quantity, int discountPercent) {
        this.item = item;
        this.quantity = quantity;
        this.discountPercent = discountPercent;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }
}
