package com.delmon.deliverymonitoring.product;

import com.delmon.deliverymonitoring.security.IdGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "idGenerator")
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    private float price;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Float.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
