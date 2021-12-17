/*
package com.delmon.deliverymonitoring.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Test
    void existsByName() {
        // given
        String name = "name";
        repository.save(new Product(name));

        // when
        boolean exists = repository.existsByName(name);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void existsByInvalidName() {
        // given
        String name = "name";
        repository.save(new Product("notName"));

        // when
        boolean exists = repository.existsByName(name);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void findByName() {
        // given
        String name = "name";
        Product givenProduct = new Product(name);
        repository.save(givenProduct);

        // when
        Product product = repository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(product).isEqualTo(givenProduct);
    }

    @Test
    void findByInvalidName() {
        // given
        String name = "name";
        Product givenProduct = new Product("invalidName");
        repository.save(givenProduct);

        // when
        Product product = repository.findByName(name)
                .orElse(null);

        // then
        assertThat(product).isEqualTo(null);
    }

    @Test
    void deleteByName() {
        // given
        String name = "name";
        Product givenProduct = new Product("invalidName");
        repository.save(givenProduct);

        // when
        repository.deleteByName(name);
        boolean exists = repository.existsByName(name);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void deleteByInvalidName() {
        // given
        String name = "name";
        Product givenProduct = new Product("invalidName");
        repository.save(givenProduct);

        // when
        try {
            repository.deleteByName(name);
        } catch (Exception ignored) {}
    }
}*/
