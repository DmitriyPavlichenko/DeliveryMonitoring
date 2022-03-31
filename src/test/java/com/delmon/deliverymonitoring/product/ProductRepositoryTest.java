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
        repository.save(new Product(name, 5f));

        // when
        boolean exists = repository.existsByName(name);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void notExistsByName() {
        // when
        boolean exists = repository.existsByName("name");

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void canFindByName() {
        // given
        String name = "name";
        Product givenProduct = new Product(name, 5f);
        repository.save(givenProduct);

        // when
        Product product = repository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(product).isEqualTo(givenProduct);
    }

    @Test
    void cannotFindByName() {
        // when
        Product product = repository.findByName("name")
                .orElse(null);

        // then
        assertThat(product).isNull();
    }

    @Test
    void canDeleteByName() {
        // given
        String name = "name";
        Product givenProduct = new Product("invalidName", 5f);
        repository.save(givenProduct);

        // when
        repository.deleteByName(name);
        boolean exists = repository.existsByName(name);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void cannotDeleteByName() {
        // given
        String name = "name";

        // when
        repository.deleteByName(name);
        boolean exists = repository.existsByName(name);

        //then
        assertThat(exists).isFalse();
    }
}
