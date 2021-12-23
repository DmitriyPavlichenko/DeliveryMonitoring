package com.delmon.deliverymonitoring.authentication;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {
    private AuthenticationService service;

    @GetMapping
    @RequestMapping(produces = "application/json")
    public AuthenticationResponse authenticate() {
        return service.authenticate();
    }
}
