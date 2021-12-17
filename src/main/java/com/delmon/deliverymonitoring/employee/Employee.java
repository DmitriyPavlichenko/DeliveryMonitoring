package com.delmon.deliverymonitoring.employee;

import com.delmon.deliverymonitoring.security.user.ApplicationUserRole;
import com.delmon.deliverymonitoring.security.IdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "idGenerator")
    @Column(nullable = false, updatable = false)
    private long id;
    private String firstName;
    private String lastName;
    @Enumerated
    private ApplicationUserRole role;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    public Employee(String firstName, String lastName, ApplicationUserRole role, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && role == employee.role && Objects.equals(phoneNumber, employee.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role, phoneNumber);
    }
}
