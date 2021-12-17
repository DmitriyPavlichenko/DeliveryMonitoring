package com.delmon.deliverymonitoring.product;

import com.delmon.deliverymonitoring.security.IdGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ProductUnit implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "idGenerator")
    @Column(nullable = false, updatable = false)
    private Long id;
    @OneToOne
    @JoinColumn(unique = true, nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUnit that = (ProductUnit) o;
        return Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity);
    }
}
