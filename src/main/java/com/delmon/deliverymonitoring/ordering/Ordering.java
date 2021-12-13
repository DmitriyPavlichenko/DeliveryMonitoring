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
    @SequenceGenerator(
            name = "ordering_sequence",
            sequenceName = "ordering_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ordering_sequence"
    )
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false)
    private String uuid;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private List<Product> products;
    @OneToOne(targetEntity = Employee.class)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Employee employee;
    @OneToOne(targetEntity = Department.class)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private LocalDateTime date;

    public Ordering(String uuid, List<Product> products, Employee employee, Department department, LocalDateTime date) {
        this.uuid = uuid;
        this.products = products;
        this.employee = employee;
        this.department = department;
        this.date = date;
    }
}
