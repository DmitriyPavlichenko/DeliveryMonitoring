package com.delmon.deliverymonitoring.authentication;

import com.delmon.deliverymonitoring.security.user.ApplicationUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.delmon.deliverymonitoring.employee.EmployeeRole.ADMINISTRATOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private ApplicationUserService applicationUserService;
    private AuthenticationService service;

    @BeforeEach
    void setUp() {
        service = new AuthenticationService(applicationUserService);
    }

    @Mock
    private Authentication authentication;

    @Test
    void canAuthenticateAsAdmin() {
        // given
        given(authentication.getName()).willReturn("admin");

        // when
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
}
