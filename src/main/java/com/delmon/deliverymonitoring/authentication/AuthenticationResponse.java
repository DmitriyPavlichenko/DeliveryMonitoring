package com.delmon.deliverymonitoring.authentication;

import com.delmon.deliverymonitoring.employee.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class AuthenticationResponse {
    private String username;
    private String firstName;
    private String lastName;
    private EmployeeRole employeeRole;
    private Boolean locked;
    private Boolean enabled;
}
