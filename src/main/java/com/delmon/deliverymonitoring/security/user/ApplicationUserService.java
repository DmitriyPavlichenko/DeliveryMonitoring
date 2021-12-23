package com.delmon.deliverymonitoring.security.user;

import com.delmon.deliverymonitoring.employee.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private ApplicationUserRepository repository;

    public ApplicationUser findUserByPhoneNumber(String phoneNumber) {
        return repository.findAppUserByEmployee_PhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + phoneNumber + " phone number isn't exists"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findAppUserByEmployee_PhoneNumber(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + s + " phone number isn't exists"));
    }

    public void singUpUser(ApplicationUser user) {
        final Employee employee = user.getEmployee();
        if (repository.existsByEmployee_PhoneNumber(employee.getPhoneNumber())) {
            throw new IllegalStateException(employee.getPhoneNumber() + " is already registered");
        }

        repository.save(user);
    }

    public void deleteUserByPhoneNumber(String phoneNumber) {
        //  TODO: phone validation
        if (!repository.existsByEmployee_PhoneNumber(phoneNumber)) {
            throw new IllegalStateException("Invalid credentials");
        }

        repository.deleteByEmployee_PhoneNumber(phoneNumber);
    }
}
