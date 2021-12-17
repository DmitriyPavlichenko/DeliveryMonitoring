package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.product.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class OrderingRequest {
    private final String uuid;
    private final List<ProductUnit> productUnitList;

    private final Long employeeId;
    private final Long departmentId;
    private final LocalDateTime dateTime;
}
