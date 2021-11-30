package com.delmon.deliverymonitoring.department;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Department implements Serializable {
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false)
    private String address;

    public Department(String address) {
        this.address = address;
    }
}
