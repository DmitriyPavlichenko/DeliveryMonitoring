package com.delmon.deliverymonitoring.employee;

import com.delmon.deliverymonitoring.security.user.ApplicationUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String uuid;
    private String firstName;
    private String lastName;
    @Enumerated
    @Column(nullable = false)
    private EmployeeRole role;
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    public Employee(String firstName, String lastName, EmployeeRole role, String phoneNumber) {
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
        return Objects.equals(uuid, employee.uuid) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && role == employee.role && Objects.equals(phoneNumber, employee.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstName, lastName, role, phoneNumber);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "uuid='" + uuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
