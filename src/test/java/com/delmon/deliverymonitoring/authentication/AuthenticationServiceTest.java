package com.delmon.deliverymonitoring.authentication;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.security.user.ApplicationUser;
import com.delmon.deliverymonitoring.security.user.ApplicationUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.delmon.deliverymonitoring.employee.EmployeeRole.ADMINISTRATOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private ApplicationUserService applicationUserService;
    private AuthenticationService service;

    @BeforeEach
    void setUp() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        service = new AuthenticationService(applicationUserService);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void canAuthenticateAsAdmin() {
        // when
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("admin");
        AuthenticationResponse response = service.authenticate();

        // then
        assertThat(response).isEqualTo(new AuthenticationResponse(
                "admin",
                "adminFName",
                "adminLName",
                ADMINISTRATOR,
                false,
                true
        ));
    }

    @Test
    void canAuthenticateAsUser() {
        // given
        given(applicationUserService.findUserByPhoneNumber(anyString())).willReturn(new ApplicationUser(new Employee(
                "firstName",
                "lastName",
                ADMINISTRATOR,
                "number"),
                "pass"
        ));

        // when
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("anyString()");
        AuthenticationResponse response = service.authenticate();

        // then
        assertThat(response).isEqualTo(new AuthenticationResponse(
                "number",
                "firstName",
                "lastName",
                ADMINISTRATOR,
                false,
                true
        ));
    }
}
