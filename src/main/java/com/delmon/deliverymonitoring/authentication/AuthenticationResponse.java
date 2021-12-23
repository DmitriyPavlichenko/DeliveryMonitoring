package com.delmon.deliverymonitoring.authentication;

import com.delmon.deliverymonitoring.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private String uuid;
    private Employee employee;
    private Boolean locked;
    private Boolean enabled;
}
