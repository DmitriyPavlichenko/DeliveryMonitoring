package com.delmon.deliverymonitoring.security.user;

import com.delmon.deliverymonitoring.employee.Employee;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository repository;

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
}
