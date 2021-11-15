package com.delmon.deliverymonitoring.model;

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
    private WarehouseWorker worker;
    @OneToOne(optional = false)
    private Department department;
    @Column(nullable = false)
    private LocalDateTime date;

    public Ordering(List<Product> products, WarehouseWorker worker, Department department, LocalDateTime date) {
        this.products = products;
        this.worker = worker;
        this.department = department;
        this.date = date;
    }
}
