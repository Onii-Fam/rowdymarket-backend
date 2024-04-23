package com.example.Rowdyback.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;
    private String code;
    private Double discountValue;
    private Boolean isPercentage;
    private java.util.Date expiryDate;

    // Constructors, getters, and setters
    public Discount() {}

    public Discount(String code, Double discountValue, Boolean isPercentage, java.util.Date expiryDate) {
        this.code = code;
        this.discountValue = discountValue;
        this.isPercentage = isPercentage;
        this.expiryDate = expiryDate;
    }

    public Long getDiscountId() { return discountId; }
    public String getCode() { return code; }
    public Double getDiscountValue() { return discountValue; }
    public Boolean getIsPercentage() { return isPercentage; }
    public java.util.Date getExpiryDate() { return expiryDate; }

    public void setDiscountId(Long discountId) { this.discountId = discountId; }
    public void setCode(String code) { this.code = code; }
    public void setDiscountValue(Double discountValue) { this.discountValue = discountValue; }
    public void setIsPercentage(Boolean isPercentage) { this.isPercentage = isPercentage; }
    public void setExpiryDate(java.util.Date expiryDate) { this.expiryDate = expiryDate; }
}
