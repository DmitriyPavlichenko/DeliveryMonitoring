package com.delmon.deliverymonitoring.security.user;

import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.security.IdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ApplicationUser implements UserDetails, Serializable {
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "idGenerator")
    private Long id;
    @OneToOne(targetEntity = Employee.class)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Employee employee;
    private String password;
    private boolean locked = false;
    private boolean enabled = true;

    public ApplicationUser(Employee employee, String password) {
        this.employee = employee;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return employee.getRole().getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return employee.getPhoneNumber();
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
        return locked == user.locked && enabled == user.enabled && Objects.equals(id, user.id) && Objects.equals(employee, user.employee)  && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, password, locked, enabled);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", employee=" + employee +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                '}';
    }
}
