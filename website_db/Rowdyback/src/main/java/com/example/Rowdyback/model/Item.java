package com.example.Rowdyback.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String name;
    private String description;
    private Double price;
    private Integer quantityAvailable;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Item() {}

    public Item(String name, String description, Double price, Integer quantityAvailable, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.imageUrl = imageUrl;
    }

    // Getters
    public Long getItemId() { return itemId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public Integer getQuantityAvailable() { return quantityAvailable; }
    public String getImageUrl() { return imageUrl; }
    public User getUser() { return user; }

    // Setters
    public void setItemId(Long itemId) { this.itemId = itemId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(Double price) { this.price = price; }
    public void setQuantityAvailable(Integer quantityAvailable) { this.quantityAvailable = quantityAvailable; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setUser(User user) { this.user = user;}

}

