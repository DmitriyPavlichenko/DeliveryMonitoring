package com.delmon.deliverymonitoring.department;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Department implements Serializable {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false, unique = true)
    private String address;

    public Department(String address) {
        this.address = address;
    }
}
