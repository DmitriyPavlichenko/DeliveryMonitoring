package com.delmon.deliverymonitoring.registration;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import com.delmon.deliverymonitoring.employee.EmployeeRole;
import com.delmon.deliverymonitoring.security.PasswordEncoderConfiguration;
import com.delmon.deliverymonitoring.security.user.ApplicationUser;
import com.delmon.deliverymonitoring.security.user.ApplicationUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {
    private RegistrationService service;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private PasswordEncoderConfiguration passwordEncoder;
    @Mock
    private ApplicationUserService applicationUserService;

    @BeforeEach
    void setUp() {
        service = new RegistrationService(employeeRepository, passwordEncoder, applicationUserService);
    }


    @Test
    void canRegister() {
        // given
        RegistrationRequest request = new RegistrationRequest("uuid", "pass");
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, "number");
        given(employeeRepository.findByUuid(request.getEmployeeUuid())).willReturn(Optional.of(employee));

        // when
        service.register(request);

        // then
        verify(passwordEncoder).encode(anyString());
        verify(applicationUserService).singUpUser(any(ApplicationUser.class));
    }

    @Test
    void cannotRegister() {
        // given
        RegistrationRequest request = new RegistrationRequest("uuid", "pass");

        // when
        Throwable throwable = catchThrowable(() -> service.register(request));

        // then
        assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessageContaining("Employee isn't exist");
    }

    @Test
    void canDelete() {
        // given
        String phoneNumber = "number";

        // when
        service.delete(phoneNumber);

        // then
        verify(applicationUserService).deleteUserByPhoneNumber(phoneNumber);
    }
}
