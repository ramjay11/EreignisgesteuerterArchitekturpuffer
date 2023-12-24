package com.ramjava.product.command.service.repository;

import com.ramjava.product.command.service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
