package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.model.Department;
import com.delmon.deliverymonitoring.model.Ordering;
import com.delmon.deliverymonitoring.model.Product;
import com.delmon.deliverymonitoring.model.WarehouseWorker;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class OrderingRepositoryTest {
    @Autowired
    private OrderingRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseWorkerRepository warehouseWorkerRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    private Ordering ordering;
    private final String uuid = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        Product givenProduct = new Product("name", 3.5f, 5);
        productRepository.save(givenProduct);
        WarehouseWorker givenWarehouseWorker = new WarehouseWorker("firstName", "lastName", "number");
        warehouseWorkerRepository.save(givenWarehouseWorker);
        Department givenDepartment = new Department("address");
        departmentRepository.save(givenDepartment);
        ordering = new Ordering(Lists.newArrayList(givenProduct), givenWarehouseWorker, givenDepartment, LocalDateTime.now());
        ordering.setUuid(uuid);
    }

    @Test
    void existsByUuid() {
        // given
        repository.save(ordering);

        // when
        boolean exists = repository.existsByUuid(uuid);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void existsByInvalidUuid() {
        // given
        ordering.setUuid("invalidUuid");
        repository.save(ordering);

        // when
        boolean exists = repository.existsByUuid(uuid);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void findByUuid() {
        // given
        repository.save(this.ordering);

        // when
        Ordering ordering = repository.findByUuid(uuid).orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(ordering).isEqualTo(this.ordering);
    }

    @Test
    void findByInvalidUuid() {
        // given
        this.ordering.setUuid("invalidUuid");
        repository.save(this.ordering);

        // when
        Ordering ordering = repository.findByUuid(uuid).orElse(null);

        // then
        assertThat(ordering).isEqualTo(null);
    }

    @Test
    void deleteByUuid() {
        // given
        repository.save(this.ordering);

        // when
        repository.deleteByUuid(uuid);
        boolean exists = repository.existsByUuid(uuid);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void deleteByInvalidUuid() {
        // given
        ordering.setUuid("invalidUuid");
        repository.save(this.ordering);

        // when
        try {
            repository.deleteByUuid(uuid);
        } catch (Exception ignored) { }
    }
}