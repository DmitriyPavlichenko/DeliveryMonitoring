package com.delmon.deliverymonitoring.registration;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import com.delmon.deliverymonitoring.security.PasswordEncoderConfiguration;
import com.delmon.deliverymonitoring.security.user.ApplicationUser;
import com.delmon.deliverymonitoring.security.user.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoderConfiguration passwordEncoder;
    private final ApplicationUserService applicationUserService;


    public void register(RegistrationRequest request) {
        Employee employee = employeeRepository.findByUuid(request.getEmployeeUuid())
                .orElseThrow(() -> new IllegalStateException("Employee isn't exist"));

        ApplicationUser user = new ApplicationUser(employee, passwordEncoder.encode(request.getPassword()));
        applicationUserService.singUpUser(user);
    }

    public void delete(String phoneNumber) {
        applicationUserService.deleteUserByPhoneNumber(phoneNumber);
    }
}
