package com.HEB.ShoppingCart.entities;

import java.math.BigDecimal;

public class FeatureOneDto {
    private String description;
    private BigDecimal grandTotal;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
