package com.HEB.ShoppingCart.repositories;

import com.HEB.ShoppingCart.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponsRepository extends JpaRepository<Coupon, Long> {
}
