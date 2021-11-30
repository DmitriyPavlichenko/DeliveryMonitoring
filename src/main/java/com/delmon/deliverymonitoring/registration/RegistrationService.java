package com.delmon.deliverymonitoring.registration;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private EmployeeService service;

    public void singUpEmployee(RegistrationRequest request) {
        if (service.existEmployeeByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalStateException("Employee with given phone number already exists");
        }

        Employee employee = new Employee(request.getFirstName(), request.getLastName(),
                request.getRole(), request.getPhoneNumber(),
                // TODO: password encryption
                request.getPassword());

        service.singUpEmployee(employee);
    }

    // TODO: update & delete methods
}
