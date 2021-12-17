package com.delmon.deliverymonitoring.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public void saveNewEmployee(Employee employee) {
        // TODO: phone number validation
        if (employeeRepository.existsByPhoneNumber(employee.getPhoneNumber())) {
            throw new IllegalStateException(employee.getPhoneNumber() + " is already registered");
        }

        employeeRepository.save(employee);
    }

    public Employee findEmployee(String phoneNumber) {
        return employeeRepository.findEmployeeByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalStateException("Invalid phone number"));
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeByPhoneNumber(String phoneNumber) {
        employeeRepository.deleteByPhoneNumber(phoneNumber);
    }
}
