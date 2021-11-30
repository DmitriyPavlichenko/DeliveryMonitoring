package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.Department;
import com.delmon.deliverymonitoring.employee.Employee;
import com.delmon.deliverymonitoring.employee.Role;
import com.delmon.deliverymonitoring.product.Product;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderingServiceTest {
    @Mock
    private OrderingRepository mockedRepository;
    private OrderingService service;

    @BeforeEach
    void setUp() {
        service = new OrderingService(mockedRepository);
    }

    @Test
    void saveNewOrdering() {
        // given
        Ordering givenOrdering = new Ordering(Lists.newArrayList(new Product("name", 3.5f, 5)),
                new Employee("firstName", "lastName", Role.ADMIN, "number", "pass"),
                new Department("address"), LocalDateTime.now());
        givenOrdering.setUuid("uuid");
        given(mockedRepository.existsByUuid(anyString())).willReturn(false);

        // when
        service.saveNewOrdering(givenOrdering);

        // then
        verify(mockedRepository).save(givenOrdering);
    }

    @Test
    void findOrderingByUuid() {
        // given
        Ordering givenOrdering = new Ordering(Lists.newArrayList(new Product("name", 3.5f, 5)),
                new Employee("firstName", "lastName", Role.ADMIN, "number", "pass"),
                new Department("address"), LocalDateTime.now());
        String uuid = "uuid";
        given(mockedRepository.findByUuid(anyString())).willReturn(java.util.Optional.of(givenOrdering));

        // when
        service.findOrderingByUuid(uuid);

        // then
        verify(mockedRepository).findByUuid(uuid);
    }

    @Test
    void findAllOrdering() {
        // when
        service.findAllOrdering();

        // then
        verify(mockedRepository).findAll();
    }

    @Test
    void deleteOrderingByUuid() {
        // given
        String uuid = "uuid";
        given(!mockedRepository.existsByUuid(anyString())).willReturn(true);

        // when
        service.deleteOrderingByUuid(uuid);

        // then
        verify(mockedRepository).deleteByUuid(uuid);
    }
}