package com.delmon.deliverymonitoring.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    // TODO with only Role.ADMIN access
    private RegistrationService service;

    @PostMapping
    public void register(@RequestBody RegistrationRequest request) {
        service.singUpEmployee(request);
    }
}
