package com.HEB.ShoppingCart.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class Constant {

    @Id
    @Column(unique = true)
    private String constantKey;
    public String getConstantKey() {
        return constantKey;
    }
    public void setConstantKey(String constantKey) {
        this.constantKey = constantKey;
    }

    private String constantValue;
    public String getConstantValue() {
        return constantValue;
    }
    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }

    private String constantType;
    public String getConstantType() {
        return constantType;
    }
    public void setConstantType(String constantType) {
        this.constantType = constantType;
    }

    public Constant() {}
}
