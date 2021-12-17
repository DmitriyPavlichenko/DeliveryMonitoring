package com.delmon.deliverymonitoring.registration;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {
    private final RegistrationService service;

    @PostMapping
    @PreAuthorize("hasAuthority('registration:register')")
    public void register(@RequestBody RegistrationRequest request) {
        service.register(request);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('registration:delete')")
    public void delete(@RequestParam String phoneNumber) {
        service.delete(phoneNumber);
    }
}
