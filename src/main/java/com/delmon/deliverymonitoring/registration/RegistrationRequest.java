package com.delmon.deliverymonitoring.registration;

import com.delmon.deliverymonitoring.employee.Role;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private String password;
}
