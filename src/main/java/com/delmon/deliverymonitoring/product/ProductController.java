package com.delmon.deliverymonitoring.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/supply/product")
public class ProductController {
    private ProductService service;

    @PostMapping
    public void saveProduct(@RequestBody Product product) {
        service.saveNewProduct(product);
    }

    @GetMapping(path = "find")
    public Product findProduct(@RequestParam String name) {
        return service.findProductByName(name);
    }

    @GetMapping(path = "findall")
    public List<Product> findAllProducts() {
        return service.findAllProducts();
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam String name) {
        service.deleteProductByName(name);
    }
}
