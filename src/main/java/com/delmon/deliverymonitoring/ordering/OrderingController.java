package com.delmon.deliverymonitoring.ordering;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/warehouse/ordering")
public class OrderingController {
    private OrderingService service;

    @PostMapping
    @PreAuthorize("hasAuthority('order:save')")
    public void saveOrder(@RequestBody OrderingRequest orderingRequest) {
        service.saveNewOrdering(orderingRequest);
    }

    @GetMapping(path = "find")
    @PreAuthorize("hasAuthority('order:find')")
    public Ordering findOrder(@RequestParam String uuid) {
        return service.findOrderingByUuid(uuid);
    }

    @GetMapping(path = "findall")
    @PreAuthorize("hasAuthority('order:find')")
    public List<Ordering> findAllOrders() {
        return service.findAllOrdering();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('department:delete')")
    public void deleteOrdering(@RequestParam String uuid) {
        service.deleteOrderingByUuid(uuid);
    }
}
