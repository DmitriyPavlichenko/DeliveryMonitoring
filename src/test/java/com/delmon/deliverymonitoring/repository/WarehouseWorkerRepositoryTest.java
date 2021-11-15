package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.model.WarehouseWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class WarehouseWorkerRepositoryTest {
    @Autowired
    private WarehouseWorkerRepository repository;

    @Test
    void existsByPhoneNumber() {
        // given
        String phoneNumber = "number";
        repository.save(new WarehouseWorker("firstName", "lastName", phoneNumber));

        // when
        boolean exists = repository.existsByPhoneNumber(phoneNumber);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void existsByInvalidPhoneNumber() {
        // given
        String phoneNumber = "number";
        repository.save(new WarehouseWorker("firstName", "lastName", "invalidNumber"));

        // when
        boolean exists = repository.existsByPhoneNumber(phoneNumber);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void findByPhoneNumber() {
        // given
        String phoneNumber = "number";
        WarehouseWorker givenWarehouseWorker = new WarehouseWorker("firstName", "lastName", phoneNumber);
        repository.save(givenWarehouseWorker);

        // when
        WarehouseWorker warehouseWorker = repository.findByPhoneNumber(phoneNumber)
                .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(warehouseWorker).isEqualTo(givenWarehouseWorker);
    }

    @Test
    void findByInvalidPhoneNumber() {
        // given
        String phoneNumber = "number";
        WarehouseWorker givenWarehouseWorker = new WarehouseWorker("firstName", "lastName", "invalidNumber");
        repository.save(givenWarehouseWorker);

        // when
        WarehouseWorker warehouseWorker = repository.findByPhoneNumber(phoneNumber)
                .orElse(null);

        // then
        assertThat(warehouseWorker).isEqualTo(null);
    }

    @Test
    void deleteByPhoneNumber() {
        // given
        String phoneNumber = "number";
        WarehouseWorker givenWarehouseWorker = new WarehouseWorker("firstName", "lastName", phoneNumber);
        repository.save(givenWarehouseWorker);

        // when
        repository.deleteByPhoneNumber(phoneNumber);
        boolean exists = repository.existsByPhoneNumber(phoneNumber);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void deleteByInvalidPhoneNumber() {
        // given
        String phoneNumber = "number";
        WarehouseWorker givenWarehouseWorker = new WarehouseWorker("firstName", "lastName", "invalidNumber");
        repository.save(givenWarehouseWorker);

        // when
        try {
            repository.deleteByPhoneNumber(phoneNumber);
        } catch (Exception ignored) { }
    }
}