package com.delmon.deliverymonitoring.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private Boolean isAuthenticated;
}
