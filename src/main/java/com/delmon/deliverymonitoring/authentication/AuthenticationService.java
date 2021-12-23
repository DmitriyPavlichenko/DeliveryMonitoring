package com.delmon.deliverymonitoring.authentication;

import com.delmon.deliverymonitoring.security.user.ApplicationUser;
import com.delmon.deliverymonitoring.security.user.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private ApplicationUserService userService;

    public AuthenticationResponse authenticate() {
        ApplicationUser user =
                userService.findUserByPhoneNumber(SecurityContextHolder.getContext().getAuthentication().getName());
        return new AuthenticationResponse(
                user.getEmployee().getPhoneNumber(),
                user.getEmployee().getFirstName(),
                user.getEmployee().getLastName(),
                user.getEmployee().getRole(),
                user.getLocked(),
                user.getEnabled()
        );
    }

}
