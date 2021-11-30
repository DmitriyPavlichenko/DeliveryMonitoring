package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.department.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository repository;

    @Test
    void existsByAddress() {
        // given
        String address = "Address";
        repository.save(new Department(address));

        // when
        boolean exists = repository.existsByAddress(address);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void existsByInvalidAddress() {
        // given
        String address = "Address";
        repository.save(new Department("notAddress"));

        // when
        boolean exists = repository.existsByAddress(address);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void findByAddress() {
        // given
        String address = "address";
        Department givenDepartment = new Department(address);
        repository.save(givenDepartment);

        // when
        Department department = repository.findByAddress(address)
                .orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(department).isEqualTo(givenDepartment);
    }

    @Test
    void findByInvalidAddress() {
        // given
        String address = "address";
        Department givenDepartment = new Department("invalidAddress");
        repository.save(givenDepartment);

        // when
        Department department = repository.findByAddress(address)
                .orElse(null);

        // then
        assertThat(department).isEqualTo(null);
    }

    @Test
    void deleteByAddress() {
        // given
        String address = "address";
        Department givenDepartment = new Department(address);
        repository.save(givenDepartment);

        // when
        repository.deleteByAddress(address);
        boolean exists = repository.existsByAddress(address);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void deleteByInvalidAddress() {
        // given
        String address = "address";
        Department givenDepartment = new Department("invalidAddress");
        repository.save(givenDepartment);

        // when
        try {
            repository.deleteByAddress(address);
        } catch (Exception ignored) {}
    }
}