package com.HEB.ShoppingCart.entities;

import java.math.BigDecimal;

public class FeatureFourDto {
    private String description;
    private BigDecimal subTotal;
    private BigDecimal nontaxableSubTotal;
    private BigDecimal taxableSubTotal;
    private BigDecimal discountTotal;
    private BigDecimal subTotalAfterDiscounts;
    private BigDecimal nontaxableSubTotalAfterDiscounts;
    private BigDecimal taxableSubTotalAfterDiscounts;
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

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getSubTotalAfterDiscounts() {
        return subTotalAfterDiscounts;
    }

    public void setSubTotalAfterDiscounts(BigDecimal subTotalAfterDiscounts) {
        this.subTotalAfterDiscounts = subTotalAfterDiscounts;
    }

    public BigDecimal getNontaxableSubTotalAfterDiscounts() {
        return nontaxableSubTotalAfterDiscounts;
    }

    public void setNontaxableSubTotalAfterDiscounts(BigDecimal nontaxableSubTotalAfterDiscounts) {
        this.nontaxableSubTotalAfterDiscounts = nontaxableSubTotalAfterDiscounts;
    }

    public BigDecimal getTaxableSubTotalAfterDiscounts() {
        return taxableSubTotalAfterDiscounts;
    }

    public void setTaxableSubTotalAfterDiscounts(BigDecimal taxableSubTotalAfterDiscounts) {
        this.taxableSubTotalAfterDiscounts = taxableSubTotalAfterDiscounts;
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
