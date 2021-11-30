package com.delmon.deliverymonitoring.employee;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmployeeService implements UserDetailsService {
    private EmployeeRepository employeeRepository;

    public void singUpEmployee(Employee employee) {
        if (employeeRepository.existsByPhoneNumber(employee.getPhoneNumber())) {
            throw new IllegalStateException(employee.getPhoneNumber() + " is already registered");
        }
        // TODO: phone number validation
        employeeRepository.save(employee);
    }

    public boolean existEmployeeByPhoneNumber(String phoneNumber) {
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return employeeRepository.findEmployeeByPhoneNumber(s)
                .orElseThrow(() -> new UsernameNotFoundException("Employee with " + s + " phone number doesn't exist"));
    }
}
