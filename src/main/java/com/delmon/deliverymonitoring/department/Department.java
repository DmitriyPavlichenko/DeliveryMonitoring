package com.delmon.deliverymonitoring.department;

import com.delmon.deliverymonitoring.security.IdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Department implements Serializable {
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "idGenerator")
    @Column(nullable = false, updatable = false)
    private long id;
    @Column(nullable = false, unique = true)
    private String address;

    public Department(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }
}
