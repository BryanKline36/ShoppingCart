package com.HEB.ShoppingCart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
public class Coupon {

    private String couponName;
    public String getCouponName() {
        return couponName;
    }
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    @Id
    private Long appliedSku;
    public Long getAppliedSku() {
        return appliedSku;
    }
    public void setAppliedSku(Long appliedSku) {
        this.appliedSku = appliedSku;
    }

    private BigDecimal discountPrice;
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Coupon() {}
}
