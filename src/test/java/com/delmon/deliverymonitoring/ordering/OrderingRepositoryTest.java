package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.department.DepartmentRepository;
import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import com.delmon.deliverymonitoring.employee.EmployeeRole;
import com.delmon.deliverymonitoring.product.Product;
import com.delmon.deliverymonitoring.product.ProductRepository;
import com.delmon.deliverymonitoring.product.ProductUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class OrderingRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private OrderingRepository repository;

    private Ordering ordering;

    @BeforeEach
    void setUp() {
        Product product = new Product("name", 0f);
        productRepository.save(product);
        ProductUnit productUnit = new ProductUnit();
        productUnit.setProduct(product);
        productUnit.setQuantity(5);
        Department department = new Department("address");
        departmentRepository.save(department);
        Employee employee = new Employee("firstName"
                , "lastName", EmployeeRole.ADMINISTRATOR, "phoneNumber");
        employeeRepository.save(employee);
        ordering = new Ordering(
                Collections.singletonList(productUnit),
                employee,
                department,
                LocalDateTime.now());

        repository.save(ordering);
    }

    @Test
    void existsByUuid() {
        // given
        String uuid = ordering.getUuid();

        // when
        boolean exists = repository.existsByUuid(uuid);

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void notExistsByUuid() {
        // given
        String uuid = "uuid";

        // when
        boolean exists = repository.existsByUuid(uuid);

        // then
        assertThat(exists).isFalse();
    }

    @Test
    void canFindByUuid() {
        // given
        String uuid = ordering.getUuid();

        // when
        Ordering orderingByUuid = repository.findByUuid(uuid).orElseThrow(NullPointerException::new);

        // then
        assertThat(orderingByUuid).isEqualTo(ordering);
    }

    @Test
    void cannotFindByUuid() {
        // when
        Ordering orderingByUuid = repository.findByUuid("uuid").orElse(null);

        // then
        assertThat(orderingByUuid).isNull();
    }

    @Test
    void canDeleteByUuid() {
        // given
        String uuid = ordering.getUuid();

        // when
        repository.deleteByUuid(uuid);
        boolean exists = repository.existsByUuid(uuid);

        // then
        assertThat(exists).isFalse();

    }

    @Test
    void cannotDeleteByUuid() {
        // given
        String uuid = "uuid";

        // when
        repository.deleteByUuid(uuid);
        boolean exists = repository.existsByUuid(uuid);

        // then
        assertThat(exists).isFalse();
    }
}
