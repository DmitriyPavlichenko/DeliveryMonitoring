package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.department.DepartmentRepository;
import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import com.delmon.deliverymonitoring.employee.EmployeeRole;
import com.delmon.deliverymonitoring.product.ProductUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderingServiceTest {
    @Mock
    private OrderingRepository repository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    private OrderingService service;

    @BeforeEach
    void setUp() {
        service = new OrderingService(repository, departmentRepository, employeeRepository);
    }

    @Test
    void canSaveNewOrdering() {
        // given
        OrderingRequest request = new OrderingRequest(Collections.singletonList(new ProductUnit()), "employeeUuid",
                "departmentUuid");
        given(employeeRepository.findByUuid(anyString())).willReturn(Optional.of(new Employee()));
        given(departmentRepository.getById(request.getDepartmentUuid())).willReturn(new Department());

        // when
        service.saveNewOrdering(request);

        // then
        verify(repository).save(any(Ordering.class));
    }

    @Test
    void cannotSaveNewOrdering() {
        // given
        OrderingRequest request = new OrderingRequest(Collections.singletonList(new ProductUnit()), "employeeUuid",
                "departmentUuid");

        // when
        Throwable throwable = catchThrowable(() -> service.saveNewOrdering(request));

        // then
        assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessageContaining("Not found");
    }

    @Test
    void canFindOrderingByUuid() {
        // given
        String uuid = "uuid";
        Ordering ordering = new Ordering(
                Collections.singletonList(new ProductUnit()),
                new Employee("firstName"
                        , "lastName", EmployeeRole.ADMINISTRATOR, "phoneNumber"),
                new Department("address"),
                LocalDateTime.now());
        given(repository.findByUuid(uuid)).willReturn(Optional.of(ordering));

        // when
        service.findOrderingByUuid(uuid);

        // then
        verify(repository).findByUuid(uuid);
    }

    @Test
    void cannotFindOrderingByUuid() {
        // when
        Throwable throwable = catchThrowable(() -> service.findOrderingByUuid("uuid"));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("uuid isn't existing");
    }

    @Test
    void canFindAllOrdering() {
        // when
        service.findAllOrdering();

        // then
        verify(repository).findAll();
    }

    @Test
    void canDeleteOrderingByUuid() {
        // given
        String uuid = "uuid";
        given(repository.existsByUuid(uuid)).willReturn(true);

        // when
        service.deleteOrderingByUuid(uuid);

        // then
        verify(repository).deleteByUuid(uuid);
    }

    @Test
    void cannotDeleteOrderingByUuid() {
        // given
        String uuid = "uuid";
        given(repository.existsByUuid(uuid)).willReturn(false);

        // when
        Throwable throwable = catchThrowable(() -> service.deleteOrderingByUuid(uuid));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining(" uuid isn't existing");
    }
}
