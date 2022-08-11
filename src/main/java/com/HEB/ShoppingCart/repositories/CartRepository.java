package com.HEB.ShoppingCart.repositories;

import com.HEB.ShoppingCart.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Item, Long> {
}
