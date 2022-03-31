package com.delmon.deliverymonitoring.security.user;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ApplicationUserServiceTest {
    @Mock
    private ApplicationUserRepository repository;
    private ApplicationUserService service;

    @BeforeEach
    void setUp() {
        service = new ApplicationUserService(repository);
    }

    @Test
    void canFindUserByPhoneNumber() {
        // given
        String phoneNumber = "phoneNumber";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        given(repository.findAppUserByEmployee_PhoneNumber(phoneNumber)).willReturn(Optional.of(new ApplicationUser(employee,
                "pass")));

        // when
        ApplicationUser user = service.findUserByPhoneNumber(phoneNumber);

        // then
        assertThat(user.getEmployee().getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void cannotFindUserByPhoneNumber() {
        // when
        Throwable throwable = catchThrowable(() -> service.findUserByPhoneNumber("phoneNumber"));

        // then
        assertThat(throwable).isInstanceOf(UsernameNotFoundException.class).hasMessageContaining(" phone number isn't" +
                " exists");
    }

    @Test
    void canLoadUserByUsername() {
        // given
        String phoneNumber = "phoneNumber";
        Employee employee = new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR, phoneNumber);
        given(repository.findAppUserByEmployee_PhoneNumber(anyString())).willReturn(Optional.of(new ApplicationUser(employee,
                "pass")));

        // when
        UserDetails userDetails = service.loadUserByUsername(phoneNumber);

        // then
        assertThat(userDetails.getUsername()).isEqualTo(phoneNumber);
    }

    @Test
    void cannotLoadUserByUsername() {
        // when
        Throwable throwable = catchThrowable(() -> service.loadUserByUsername(anyString()));

        // then
        assertThat(throwable).isInstanceOf(UsernameNotFoundException.class).hasMessageContaining(" phone number isn't" +
                " exists");
    }

    @Test
    void canSingUpUser() {
        // given
        String phoneNumber = "phoneNumber";
        ApplicationUser user = new ApplicationUser(new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR,
                phoneNumber), "pass");
        given(repository.existsByEmployee_PhoneNumber(anyString())).willReturn(false);

        // when
        service.singUpUser(user);

        // then
        verify(repository).save(user);
    }

    @Test
    void cannotSingUpUser() {
        // given
        ApplicationUser user = new ApplicationUser(new Employee("firstName", "lastName", EmployeeRole.ADMINISTRATOR,
                "phoneNumber"), "pass");
        given(repository.existsByEmployee_PhoneNumber(anyString())).willReturn(true);

        // when
        Throwable throwable = catchThrowable(() -> service.singUpUser(user));

        // then
        assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessageContaining(" is already registered");
    }

    @Test
    void canDeleteUserByPhoneNumber() {
        // given
        String phoneNumber = "phoneNumber";
        given(repository.existsByEmployee_PhoneNumber(phoneNumber)).willReturn(true);

        // when
        service.deleteUserByPhoneNumber(phoneNumber);

        // then
        verify(repository).deleteByEmployee_PhoneNumber(phoneNumber);
    }

    @Test
    void cannotDeleteUserByPhoneNumber() {
        // given
        String phoneNumber = "phoneNumber";
        given(repository.existsByEmployee_PhoneNumber(phoneNumber)).willReturn(false);

        // when
        Throwable throwable = catchThrowable(() -> service.deleteUserByPhoneNumber(phoneNumber));

        // then
        assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessageContaining("Invalid credentials");
    }
}
