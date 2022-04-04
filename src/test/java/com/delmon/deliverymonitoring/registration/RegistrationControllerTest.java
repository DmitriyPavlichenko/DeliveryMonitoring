package com.delmon.deliverymonitoring.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {
    @Mock
    private RegistrationService service;
    private RegistrationController controller;

    @BeforeEach
    void setUp() {
        controller = new RegistrationController(service);
    }

    @Test
    void canRegister() {
        // given
        RegistrationRequest request = new RegistrationRequest("uuid", "pass");

        // when
        controller.register(request);

        // then
        verify(service).register(request);
    }

    @Test
    void canDelete() {
        // given
        String phoneNumber = "phoneNumber";

        // when
        controller.delete(phoneNumber);

        // then
        verify(service).delete(phoneNumber);
    }
}
