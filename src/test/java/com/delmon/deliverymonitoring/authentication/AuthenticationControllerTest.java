package com.delmon.deliverymonitoring.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
    @Mock
    private AuthenticationService service;
    private AuthenticationController controller;

    @BeforeEach
    void setUp() {
        controller = new AuthenticationController(service);
    }

    @Test
    void canAuthenticate() {
        // when
        controller.authenticate();

        // then
        verify(service).authenticate();
    }
}
