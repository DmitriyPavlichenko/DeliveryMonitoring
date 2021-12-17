package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.product.Product;
import com.delmon.deliverymonitoring.product.ProductUnit;
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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id private String uuid;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "uuid")
    private List<ProductUnit> productUnitList;
    @OneToOne(targetEntity = Employee.class)
    @JoinColumn(referencedColumnName = "uuid", nullable = false)
    private Employee employee;
    @OneToOne(targetEntity = Department.class)
    @JoinColumn(referencedColumnName = "uuid", nullable = false)
    private Department department;

    @Column(nullable = false)
    private LocalDateTime date;

    public Ordering(List<ProductUnit> productUnitList, Employee employee, Department department, LocalDateTime date) {
        this.productUnitList = productUnitList;
        this.employee = employee;
        this.department = department;
        this.date = date;
    }
}
