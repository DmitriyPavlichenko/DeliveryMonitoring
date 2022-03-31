package com.delmon.deliverymonitoring.security.user;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import com.delmon.deliverymonitoring.employee.EmployeeRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ApplicationUserRepositoryTest {
    @Autowired
    private ApplicationUserRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void canFindAppUserByEmployeePhoneNumber() {
        // given
        String phoneNumber = "phoneNumber";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        employeeRepository.save(employee);
        repository.save(new ApplicationUser(employee, "pass"));

        // when
        ApplicationUser user =
                repository.findAppUserByEmployee_PhoneNumber(phoneNumber)
                        .orElseThrow(IllegalStateException::new);

        // then
        assertThat(user.getEmployee().getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void cannotFindAppUserByEmployeePhoneNumber() {
        // when
        ApplicationUser user =
                repository.findAppUserByEmployee_PhoneNumber("phoneNumber")
                        .orElse(null);

        // then
        assertThat(user).isNull();
    }

    @Test
    void existsByEmployeePhoneNumber() {
        // given
        String phoneNumber = "phoneNumber";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        employeeRepository.save(employee);
        repository.save(new ApplicationUser(employee, "pass"));

        // when
        boolean exists = repository.existsByEmployee_PhoneNumber(phoneNumber);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void notExistsByEmployeePhoneNumber() {
        // when
        boolean exists = repository.existsByEmployee_PhoneNumber("phoneNumber");

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void canDeleteByEmployeePhoneNumber() {
        // given
        String phoneNumber = "phoneNumber";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        employeeRepository.save(employee);
        repository.save(new ApplicationUser(employee, "pass"));

        // when
        repository.deleteByEmployee_PhoneNumber(phoneNumber);
        boolean exists = repository.existsByEmployee_PhoneNumber(phoneNumber);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void cannotDeleteByEmployeePhoneNumber() {
        // when
        repository.deleteByEmployee_PhoneNumber("phoneNumber");
        boolean exists = repository.existsByEmployee_PhoneNumber("phoneNumber");

        // then
        assertThat(exists).isFalse();
    }
}
