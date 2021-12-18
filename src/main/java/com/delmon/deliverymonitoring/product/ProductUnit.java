package com.delmon.deliverymonitoring.product;

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
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String uuid;
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
        return Objects.equals(uuid, that.uuid) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, product, quantity);
    }

    @Override
    public String toString() {
        return "ProductUnit{" +
                "uuid='" + uuid + '\'' +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
