package com.HEB.ShoppingCart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
public class Item {

    private String itemName;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Id
    private Long sku;
    public Long getSku() {
        return sku;
    }
    public void setSku(Long sku) {
        this.sku = sku;
    }

    private Boolean isTaxable;
    public Boolean isIsTaxable() {
        return isTaxable;
    }
    public void setIsTaxable(Boolean taxable) {
        this.isTaxable = taxable;
    }

    private Boolean ownBrand;
    public Boolean isOwnBrand() {
        return ownBrand;
    }
    public void setOwnBrand(Boolean ownBrand) {
        this.ownBrand = ownBrand;
    }

    private BigDecimal price;
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Item() {}
}
