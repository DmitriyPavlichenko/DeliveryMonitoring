package com.delmon.deliverymonitoring.authentication;

import com.delmon.deliverymonitoring.security.user.ApplicationUser;
import com.delmon.deliverymonitoring.security.user.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.delmon.deliverymonitoring.employee.EmployeeRole.ADMINISTRATOR;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private ApplicationUserService userService;

    public AuthenticationResponse authenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ("admin".equals(authentication.getName())) {
            return new AuthenticationResponse(
                    "admin",
                    "adminFName",
                    "adminLName",
                    ADMINISTRATOR,
                    false,
                    true
            );
        }

        ApplicationUser user = userService.findUserByPhoneNumber(authentication.getName());
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
