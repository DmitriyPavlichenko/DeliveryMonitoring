package com.delmon.deliverymonitoring.employee;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Employee implements UserDetails {
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String firstName;
    private String lastName;
    @Enumerated
    private Role role;
//    @Column(nullable = false)
    private String phoneNumber;
    private String password;
    private boolean locked = false;
    private boolean enabled = true;

    public Employee(String firstName, String lastName, Role role, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(phoneNumber);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && locked == employee.locked && enabled == employee.enabled && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && role == employee.role && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role, phoneNumber, password, locked, enabled);
    }
}
