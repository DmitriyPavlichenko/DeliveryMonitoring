package com.delmon.deliverymonitoring.registration;

import com.delmon.deliverymonitoring.security.ApplicationUserRole;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {
    private final Long employeeId;
    private final String password;
}
