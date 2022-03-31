package com.delmon.deliverymonitoring.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository repository;
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService(repository);
    }

    @Test
    void canSaveNewEmployee() {
        // given
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, "phoneNumber");
        given(repository.existsByPhoneNumber(anyString())).willReturn(false);

        // when
        service.saveNewEmployee(employee);

        // then
        verify(repository).save(employee);
    }

    @Test
    void cannotSaveNewEmployee() {
        // given
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, "phoneNumber");
        given(repository.existsByPhoneNumber(anyString())).willReturn(true);

        // when
        Throwable throwable = catchThrowable(() -> service.saveNewEmployee(employee));

        // then
        assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessageContaining("is already registered");
    }

    @Test
    void canFindEmployee() {
        // given
        String phoneNumber = "phoneNumber";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        given(repository.findByPhoneNumber(phoneNumber)).willReturn(Optional.of(employee));

        // when
        Employee foundEmployee = service.findEmployee(phoneNumber);

        // then
        assertThat(foundEmployee).isEqualTo(employee);
    }

    @Test
    void cannotFindEmployees() {
        // given
        String phoneNumber = "phoneNumber";

        // when
        Throwable throwable = catchThrowable(() -> service.findEmployee(phoneNumber));

        // then
        assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessageContaining("Invalid phone number");
    }

    @Test
    void canFindAllEmployees() {
        // given
        List<Employee> employeeList = Arrays.asList(
                new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, "phoneNumber"),
                new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, "phoneNumber")
        );
        given(repository.findAll()).willReturn(employeeList);

        // when
        List<Employee> foundEmployees = service.findAllEmployees();

        // then
        assertThat(foundEmployees).isEqualTo(employeeList);
    }

    @Test
    void cannotFindAllEmployees() {
        // given
        given(repository.findAll()).willReturn(emptyList());

        // when
        List<Employee> foundEmployees = service.findAllEmployees();

        // then
        assertThat(foundEmployees.isEmpty()).isTrue();
    }

    @Test
    void canDeleteEmployeeByPhoneNumber() {
        // when
        service.deleteEmployeeByPhoneNumber(anyString());

        // then
        verify(repository).deleteByPhoneNumber(anyString());
    }
}
