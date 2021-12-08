package com.delmon.deliverymonitoring.ordering;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/warehouse/ordering")
public class OrderingController {
    private OrderingService service;

    @PostMapping
    public void saveOrder(@RequestBody OrderingRequest orderingRequest) {
        service.saveNewOrdering(orderingRequest);
    }

    @GetMapping(path = "find")
    public Ordering findOrder(@RequestParam String uuid) {
        return service.findOrderingByUuid(uuid);
    }

    @GetMapping(path = "findall")
    public List<Ordering> findAllOrders() {
        return service.findAllOrdering();
    }

    @DeleteMapping
    public void deleteOrdering(@RequestParam String uuid) {
        service.deleteOrderingByUuid(uuid);
    }
}
