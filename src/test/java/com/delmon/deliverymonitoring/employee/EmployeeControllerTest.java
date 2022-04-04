package com.delmon.deliverymonitoring.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    @Mock
    private EmployeeService service;
    private EmployeeController controller;

    @BeforeEach
    void setUp() {
        controller = new EmployeeController(service);
    }

    @Test
    void canSaveEmployee() {
        // given
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, "phoneNumber");

        // when
        controller.saveEmployee(employee);

        // then
        verify(service).saveNewEmployee(employee);
    }

    @Test
    void canFindEmployee() {
        // given
        String phoneNumber = "phoneNumber";

        // when
        controller.findEmployee(phoneNumber);

        // then
        verify(service).findEmployee(phoneNumber);
    }

    @Test
    void canFindAllEmployees() {
        // when
        controller.findAllEmployees();

        // then
        verify(service).findAllEmployees();
    }

    @Test
    void canDeleteEmployees() {
        // given
        String phoneNumber = "phoneNumber";

        // when
        controller.deleteEmployee(phoneNumber);

        // then
        verify(service).deleteEmployeeByPhoneNumber(phoneNumber);
    }
}
