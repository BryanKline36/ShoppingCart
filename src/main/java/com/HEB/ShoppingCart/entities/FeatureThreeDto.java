package com.HEB.ShoppingCart.entities;

import java.math.BigDecimal;

public class FeatureThreeDto {
    private String description;
    private BigDecimal subTotal;
    private BigDecimal nontaxableSubTotal;
    private BigDecimal taxableSubTotal;
    private BigDecimal taxTotal;
    private BigDecimal grandTotal;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getNontaxableSubTotal() {
        return nontaxableSubTotal;
    }

    public void setNontaxableSubTotal(BigDecimal nontaxableSubTotal) {
        this.nontaxableSubTotal = nontaxableSubTotal;
    }

    public BigDecimal getTaxableSubTotal() {
        return taxableSubTotal;
    }

    public void setTaxableSubTotal(BigDecimal taxableSubTotal) {
        this.taxableSubTotal = taxableSubTotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }
}
