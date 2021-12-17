package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.product.Product;
import com.delmon.deliverymonitoring.product.ProductUnit;
import com.delmon.deliverymonitoring.security.IdGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Ordering implements Serializable {
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "idGenerator")
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false)
    private String uuid;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private List<ProductUnit> productUnitList;
    @OneToOne(targetEntity = Employee.class)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Employee employee;
    @OneToOne(targetEntity = Department.class)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private LocalDateTime date;

    public Ordering(String uuid, List<ProductUnit> productUnitList, Employee employee, Department department, LocalDateTime date) {
        this.uuid = uuid;
        this.productUnitList = productUnitList;
        this.employee = employee;
        this.department = department;
        this.date = date;
    }
}
