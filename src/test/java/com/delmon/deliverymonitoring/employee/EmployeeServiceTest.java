package com.delmon.deliverymonitoring.employee;

import com.delmon.deliverymonitoring.security.user.ApplicationUserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository mockedRepository;
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService(mockedRepository);
    }

    @Test
    void singUpEmployee() {
        // given
        Employee givenEmployee = new Employee("firstName", "lastName", EmployeeRole.WAREHOUSE_WORKER,
                "number");
        given(mockedRepository.existsByPhoneNumber(any())).willReturn(false);

        // when
        service.saveNewEmployee(givenEmployee);

        // then
        verify(mockedRepository).save(givenEmployee);
    }

    /*@Test
    void existEmployeeByPhoneNumber() {
        // given
        String phoneNumber = "number";

        // when
        service.existEmployeeByPhoneNumber(phoneNumber);

        // then
        verify(mockedRepository).existsByPhoneNumber(phoneNumber);
    }*/

    /*@Test
    void loadUserByUsername() {
        // given
        String phoneNumber = "number";
        Employee givenEmployee = new Employee("firstName", "lastName", Role.ADMIN,
                "number");
        given(mockedRepository.findEmployeeByPhoneNumber(any())).willReturn(java.util.Optional.of(givenEmployee));

        // when
        service.loadUserByUsername(phoneNumber);

        // then
        verify(mockedRepository).findEmployeeByPhoneNumber(phoneNumber);
    }*/
}