package com.delmon.deliverymonitoring.employee;

import lombok.*;

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
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    @Column(nullable = false, updatable = false)
    private long id;
    private String firstName;
    private String lastName;
    @Enumerated
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
        return id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && role == employee.role && Objects.equals(phoneNumber, employee.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role, phoneNumber);
    }
}
