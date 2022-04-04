package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.product.ProductUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderingControllerTest {
    @Mock
    private OrderingService service;
    private OrderingController controller;

    @BeforeEach
    void setUp() {
        controller = new OrderingController(service);
    }

    @Test
    void canSaveOrder() {
        // given
        OrderingRequest request = new OrderingRequest(Collections.singletonList(new ProductUnit()), "EUuid", "DUuid");

        // when
        controller.saveOrder(request);

        // then
        verify(service).saveNewOrdering(request);
    }

    @Test
    void canFindOrder() {
        // given
        String uuid = "uuid";

        // when
        controller.findOrder(uuid);

        // then
        verify(service).findOrderingByUuid(uuid);
    }

    @Test
    void canFindAllOrders() {
        // when
        controller.findAllOrders();

        // then
        verify(service).findAllOrdering();
    }

    @Test
    void canDeleteOrdering() {
        // given
        String uuid = "uuid";

        // when
        controller.deleteOrdering(uuid);

        // then
        verify(service).deleteOrderingByUuid(uuid);
    }
}
