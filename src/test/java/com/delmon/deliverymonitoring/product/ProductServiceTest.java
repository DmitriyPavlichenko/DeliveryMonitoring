package com.delmon.deliverymonitoring.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
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
    void canSaveNewProduct() {
        // given
        Product givenProduct = new Product("name", 5f);
        given(mockedRepository.existsByName(anyString())).willReturn(false);

        // when
        service.saveNewProduct(givenProduct);

        // then
        verify(mockedRepository).save(givenProduct);
    }

    @Test
    void cannotSaveNewProduct() {
        // given
        Product givenProduct = new Product("name", 5f);
        given(mockedRepository.existsByName(anyString())).willReturn(true);

        // when
        Throwable throwable = catchThrowable(() -> service.saveNewProduct(givenProduct));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("name is already " +
                "existing");
    }

    @Test
    void canFindProductByName() {
        // given
        String name = "name";
        Product givenProduct = new Product("name", 5f);
        given(mockedRepository.findByName(anyString())).willReturn(Optional.of(givenProduct));

        // when
        service.findProductByName(name);

        // then
        verify(mockedRepository).findByName(name);
    }

    @Test
    void cannotFindProductByName() {
        // when
        Throwable throwable = catchThrowable(() -> service.findProductByName(anyString()));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("name address isn't " +
                "existing");
    }

    @Test
    void canFindAllProducts() {
        // when
        service.findAllProducts();

        // then
        verify(mockedRepository).findAll();
    }

    @Test
    void canDeleteProductByName() {
        // given
        String name = "name";
        given(mockedRepository.existsByName(anyString())).willReturn(true);

        // when
        service.deleteProductByName(name);

        // then
        verify(mockedRepository).deleteByName(name);
    }

    @Test
    void cannotDeleteProductByName() {
        // given
        given(mockedRepository.existsByName(anyString())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(() -> service.deleteProductByName(anyString()));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("name address isn't " +
                "existing");
    }
}
