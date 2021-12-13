package com.delmon.deliverymonitoring.employee;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/supply/employee")
public class EmployeeController {
    private EmployeeService service;

    @PostMapping
//    @PreAuthorize("hasAuthority('employee:save')")
    public void saveEmployee(@RequestBody Employee employee) {
        service.saveNewEmployee(employee);
    }

    @GetMapping(path = "find")
    @PreAuthorize("hasAuthority('employee:find')")
    public Employee findEmployee(@RequestParam String phoneNumber) {
        return service.findEmployee(phoneNumber);
    }

    @GetMapping(path = "findall")
    @PreAuthorize("hasAuthority('employee:find')")
    public List<Employee> findAllOrders() {
        return service.findAllEmployees();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('employee:delete')")
    public void deleteOrdering(@RequestParam String phoneNumber) {
        service.deleteEmployeeByPhoneNumber(phoneNumber);
    }
}
