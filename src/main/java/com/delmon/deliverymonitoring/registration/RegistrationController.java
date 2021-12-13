package com.delmon.deliverymonitoring.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {
    private final RegistrationService service;

    @PostMapping
    //@PreAuthorize("hasAuthority('registration:register')")
    public void register(@RequestBody RegistrationRequest request) {
        service.register(request);
    }
}
