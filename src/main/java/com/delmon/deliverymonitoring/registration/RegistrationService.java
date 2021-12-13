package com.delmon.deliverymonitoring.registration;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import com.delmon.deliverymonitoring.security.PasswordEncoderConfig;
import com.delmon.deliverymonitoring.security.user.ApplicationUser;
import com.delmon.deliverymonitoring.security.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoderConfig passwordEncoder;
    private final UserService userService;


    public void register(RegistrationRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new IllegalStateException("Employee isn't exist"));

        ApplicationUser user = new ApplicationUser(employee, passwordEncoder.encode(request.getPassword()));
        userService.singUpUser(user);
    }
}
