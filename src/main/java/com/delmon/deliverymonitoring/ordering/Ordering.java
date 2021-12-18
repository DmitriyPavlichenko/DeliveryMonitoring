package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.product.ProductUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Ordering implements Serializable {
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id private String uuid;

    @OneToMany
    private List<ProductUnit> productUnitList;
    @OneToOne
    @JoinColumn(nullable = false)
    private Employee employee;
    @OneToOne
    @JoinColumn(nullable = false)
    private Department department;

    @Column(nullable = false)
    private LocalDateTime date;

    public Ordering(List<ProductUnit> productUnitList, Employee employee, Department department, LocalDateTime date) {
        this.productUnitList = productUnitList;
        this.employee = employee;
        this.department = department;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordering ordering = (Ordering) o;
        return Objects.equals(uuid, ordering.uuid) && Objects.equals(productUnitList, ordering.productUnitList) && Objects.equals(employee, ordering.employee) && Objects.equals(department, ordering.department) && Objects.equals(date, ordering.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, productUnitList, employee, department, date);
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "uuid='" + uuid + '\'' +
                ", productUnitList=" + productUnitList +
                ", employee=" + employee +
                ", department=" + department +
                ", date=" + date +
                '}';
    }
}
