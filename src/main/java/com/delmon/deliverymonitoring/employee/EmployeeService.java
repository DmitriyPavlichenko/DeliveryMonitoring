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
        if (employeeRepository.existByPhoneNumber(employee.getPhoneNumber())) {
            throw new IllegalStateException(employee.getPhoneNumber() + " is already registered");
        }
        employeeRepository.save(employee);
    }

    public boolean existEmployeeByPhoneNumber(String phoneNumber) {
        return employeeRepository.existByPhoneNumber(phoneNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return employeeRepository.findAppUserByPhoneNumber(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + s + " email isn't exists"));
    }
}
