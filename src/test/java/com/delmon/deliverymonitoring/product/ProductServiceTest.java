package com.delmon.deliverymonitoring.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository mockedRepository;
    private ProductService service;

    @BeforeEach
    void setUp() {
        service = new ProductService(mockedRepository);
    }

    @Test
    void saveNewProduct() {
        // given
        Product givenProduct = new Product("name", 3.5f, 5);
        given(mockedRepository.existsByName(anyString())).willReturn(false);

        // when
        service.saveNewProduct(givenProduct);

        // then
        verify(mockedRepository).save(givenProduct);
    }

    @Test
    void findProductByName() {
        // given
        String name = "name";
        Product givenProduct = new Product("name", 3.5f, 5);
        given(mockedRepository.findByName(anyString())).willReturn(java.util.Optional.of(givenProduct));

        // when
        service.findProductByName(name);

        // then
        verify(mockedRepository).findByName(name);
    }

    @Test
    void findAllProducts() {
        // when
        service.findAllProducts();

        // then
        verify(mockedRepository).findAll();
    }

    @Test
    void deleteProductByName() {
        // given
        String name = "name";
        given(mockedRepository.existsByName(anyString())).willReturn(true);

        // when
        service.deleteProductByName(name);

        // then
        verify(mockedRepository).deleteByName(name);
    }
}