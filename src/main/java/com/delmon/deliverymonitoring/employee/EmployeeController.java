package com.delmon.deliverymonitoring.employee;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/supply/employee")
public class EmployeeController {
    private EmployeeService service;

    @PostMapping
    public void saveEmployee(@RequestBody Employee employee) {
        service.singUpEmployee(employee);
    }

    @GetMapping(path = "find")
    public Employee findEmployee(@RequestParam String phoneNumber) {
        return (Employee) service.loadUserByUsername(phoneNumber);
    }
/*
    @GetMapping(path = "findall")
    public List<Ordering> findAllOrders() {
        return service.findAllOrdering();
    }

    @DeleteMapping
    public void deleteOrdering(@RequestParam String uuid) {
        service.deleteOrderingByUuid(uuid);
    }*/
}
