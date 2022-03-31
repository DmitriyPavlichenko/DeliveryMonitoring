package com.delmon.deliverymonitoring.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;

    @Test
    void canFindEmployeeByPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        repository.save(employee);

        // when
        Optional<Employee> employeeByPhoneNumber = repository.findByPhoneNumber(phoneNumber);

        // then
        assertThat(employeeByPhoneNumber).hasValue(employee);
    }

    @Test
    void cannotFindEmployeeByPhoneNumber() {
        // given
        String phoneNumber = "number";

        // when
        Optional<Employee> employeeByPhoneNumber = repository.findByPhoneNumber(phoneNumber);

        // then
        assertThat(employeeByPhoneNumber).isEmpty();
    }

    @Test
    void existsByPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        repository.save(employee);

        // when
        boolean existsByPhoneNumber = repository.existsByPhoneNumber(phoneNumber);

        // then
        assertThat(existsByPhoneNumber).isTrue();
    }

    @Test
    void notExistsByPhoneNumber() {
        // given
        String phoneNumber = "number";

        // when
        boolean existsByPhoneNumber = repository.existsByPhoneNumber(phoneNumber);

        // then
        assertThat(existsByPhoneNumber).isFalse();
    }

    @Test
    void canDeleteByPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        repository.save(employee);

        // when
        repository.deleteByPhoneNumber(phoneNumber);

        // then
        assertThat(repository.existsByPhoneNumber(phoneNumber)).isFalse();
    }

    @Test
    void cannotDeleteByPhoneNumber() {
        // given
        String phoneNumber = "number";

        // when
        repository.deleteByPhoneNumber(phoneNumber);

        // then
        assertThat(repository.existsByPhoneNumber(phoneNumber)).isFalse();
    }

    @Test
    void canFindByUuid() {
        // given
        String phoneNumber = "number";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        repository.save(employee);
        String uuid = repository.findByPhoneNumber(phoneNumber).orElseThrow(IllegalStateException::new).getUuid();

        // when
        Optional<Employee> employeeByUUID = repository.findByUuid(uuid);

        // then
        assertThat(employeeByUUID).hasValue(employee);
    }

    @Test
    void cannotFindByUuid() {
        // given
        String uuid = "UUID";

        // when
        Optional<Employee> employeeByUUID = repository.findByUuid(uuid);

        // then
        assertThat(employeeByUUID).isEmpty();
    }
}
