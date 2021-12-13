package com.delmon.deliverymonitoring.security.user;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.security.ApplicationUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ApplicationUser implements UserDetails, Serializable {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @OneToOne(targetEntity = Employee.class)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Employee employee;
    private String username;
    private String password;
    @Enumerated
    private ApplicationUserRole role;
    private boolean locked = false;
    private boolean enabled = true;

    public ApplicationUser(Employee employee, String password) {
        this.employee = employee;
        this.username = employee.getPhoneNumber();
        this.password = password;
        this.role = employee.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        ApplicationUser user = (ApplicationUser) o;
        return locked == user.locked && enabled == user.enabled && Objects.equals(id, user.id) && Objects.equals(employee, user.employee) && role == user.role && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, role, password, locked, enabled);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", employee=" + employee +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                '}';
    }

}
