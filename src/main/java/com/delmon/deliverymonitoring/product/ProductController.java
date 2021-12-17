package com.delmon.deliverymonitoring.product;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/supply/product")
public class ProductController {
    private ProductService service;

    @PostMapping
    @PreAuthorize("hasAuthority('product:save')")
    public void saveProduct(@RequestBody Product product) {
        service.saveNewProduct(product);
    }

    @GetMapping(path = "find")
    @PreAuthorize("hasAuthority('product:find')")
    public Product findProduct(@RequestParam String name) {
        return service.findProductByName(name);
    }

    @GetMapping(path = "findall")
    @PreAuthorize("hasAuthority('product:find')")
    public List<Product> findAllProducts() {
        return service.findAllProducts();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('product:delete')")
    public void deleteProduct(@RequestParam String name) {
        service.deleteProductByName(name);
    }
}
