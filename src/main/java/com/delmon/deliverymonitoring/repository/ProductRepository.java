package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
