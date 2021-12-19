package com.delmon.deliverymonitoring.employee;

import com.delmon.deliverymonitoring.security.user.ApplicationUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository repository;

    @Test
    void findEmployeeByPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee givenEmployee = new Employee("firstName", "lastName", EmployeeRole.PRODUCT_SUPPLIER, phoneNumber);
        repository.save(givenEmployee);

        // when
        Employee employee = repository.findEmployeeByPhoneNumber(phoneNumber)
                .orElseThrow(IllegalStateException::new);

        // then
        assertThat(employee).isEqualTo(givenEmployee);
    }

    @Test
    void findEmployeeByInvalidPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee givenEmployee = new Employee("firstName", "lastName", EmployeeRole.PRODUCT_SUPPLIER, phoneNumber);
        repository.save(givenEmployee);

        // when
        Employee employee = repository.findEmployeeByPhoneNumber("invalid number")
                .orElse(null);

        // then
        assertThat(employee).isNull();
    }

    @Test
    void existByPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee givenEmployee = new Employee("firstName", "lastName", EmployeeRole.WAREHOUSE_WORKER, phoneNumber);
        repository.save(givenEmployee);

        // when
        boolean isExist = repository.existsByPhoneNumber(phoneNumber);

        // then
        assertThat(isExist).isTrue();
    }

    @Test
    void existByInvalidPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee givenEmployee = new Employee("firstName", "lastName", EmployeeRole.PRODUCT_SUPPLIER, phoneNumber);
        repository.save(givenEmployee);

        // when
        boolean isExist = repository.existsByPhoneNumber("invalidNumber");

        // then
        assertThat(isExist).isFalse();
    }

   /* @Test
    void updateEnabledByPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee givenEmployee = new Employee("firstName", "lastName", Role.ADMIN, phoneNumber);
        repository.save(givenEmployee);

        // when
        repository.updateEnabledByPhoneNumber(phoneNumber, false);
        Employee employee = repository.findEmployeeByPhoneNumber(phoneNumber)
                .orElseThrow(IllegalStateException::new);

        // then
        assertThat(employee.isEnabled()).isFalse();
    }

    @Test
    void updateEnabledByInvalidPhoneNumber() {
        // given
        String phoneNumber = "number";
        Employee givenEmployee = new Employee("firstName", "lastName", Role.ADMIN, phoneNumber);
        repository.save(givenEmployee);

        // when
        repository.updateEnabledByPhoneNumber(phoneNumber, false);
        Employee employee = repository.findEmployeeByPhoneNumber("invalid number")
                .orElse(null);

        // then
        assertThat(employee).isNull();
    }*/
}