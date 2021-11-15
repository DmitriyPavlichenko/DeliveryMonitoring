package com.delmon.deliverymonitoring.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ordering {
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    @OneToMany
    private List<Product> products;
    @OneToOne
    private WarehouseWorker worker;
    @OneToOne
    private Department department;
    private Date date;
}
