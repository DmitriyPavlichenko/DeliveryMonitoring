package com.delmon.deliverymonitoring.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService service;
    private ProductController controller;

    @BeforeEach
    void setUp() {
        controller = new ProductController(service);
    }

    @Test
    void canSaveProduct() {
        // given
        Product product = new Product("name", 0f);

        // when
        controller.saveProduct(product);

        // then
        verify(service).saveNewProduct(product);
    }

    @Test
    void canFindProduct() {
        // given
        String name = "name";

        // when
        controller.findProduct(name);

        // then
        verify(service).findProductByName(name);
    }

    @Test
    void canFindAllProducts() {
        // when
        controller.findAllProducts();

        // then
        verify(service).findAllProducts();
    }

    @Test
    void canDeleteProduct() {
        // given
        String name = "name";

        // when
        controller.deleteProduct(name);

        // then
        verify(service).deleteProductByName(name);
    }
}
