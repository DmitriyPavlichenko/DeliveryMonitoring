package com.delmon.deliverymonitoring.authentication;

import com.delmon.deliverymonitoring.security.user.ApplicationUser;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Secured("ROLE_VIEWER")
    public AuthenticationResponse authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return new AuthenticationResponse(
                authentication.getName(),
                authentication.isAuthenticated()
        );
    }
}
