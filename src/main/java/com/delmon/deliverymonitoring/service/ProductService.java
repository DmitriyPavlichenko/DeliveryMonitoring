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

    private final static String NAME_IS_EXISTING_MESSAGE = "Product with %s name is already existing";
    private final static String NAME_IS_NOT_EXISTING_MESSAGE = "Product with %s name address isn't existing";

    public void saveNewProduct(Product product) {
        // Checking if given product name is unique
        if (repository.existsByName(product.getName())) {
            throw new IllegalArgumentException(String.format(NAME_IS_EXISTING_MESSAGE, product.getName()));
        }

        repository.save(product);
    }

    public Product findProductByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(String.format(NAME_IS_NOT_EXISTING_MESSAGE, name)));
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public void deleteProductByName(String name) {
        // Checking if given name is existing
        if (!repository.existsByName(name)) {
            throw new IllegalArgumentException(String.format(NAME_IS_NOT_EXISTING_MESSAGE, name));
        }

        repository.deleteByName(name);
    }
}
