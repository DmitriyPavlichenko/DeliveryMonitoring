package com.delmon.deliverymonitoring.service;

import com.delmon.deliverymonitoring.model.Product;
import com.delmon.deliverymonitoring.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;
    // TODO: exception's messages move to constant fields

    public void saveNewProduct(Product product) {
        // Checking if given product name is unique
        if (repository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product with " + product.getName() + " is already existing");
        }

        repository.save(product);
    }

    public Product findProductByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Product with " + name + " isn't existing"));
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public void deleteProductByName(String name) {
        // Checking if given name is existing
        if (!repository.existsByName(name)) {
            throw new IllegalArgumentException("Product with " + name + " isn't existing");
        }

        repository.deleteByName(name);
    }
}
