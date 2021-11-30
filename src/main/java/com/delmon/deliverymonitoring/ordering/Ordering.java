package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Ordering implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false)
    private String uuid;
    @Column(nullable = false)
    @OneToMany
    private List<Product> products;
    @OneToOne(optional = false)
    private Employee employee;
    @OneToOne(optional = false)
    private Department department;
    @Column(nullable = false)
    private LocalDateTime date;

    public Ordering(List<Product> products, Employee employee, Department department, LocalDateTime date) {
        this.products = products;
        this.employee = employee;
        this.department = department;
        this.date = date;
    }
}
