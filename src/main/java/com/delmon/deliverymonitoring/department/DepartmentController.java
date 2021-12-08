package com.delmon.deliverymonitoring.department;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/supply/department")
public class DepartmentController {
    private DepartmentService service;

    @PostMapping
    public void saveDepartment(@RequestBody Department department) {
        service.saveNewDepartment(department);
    }

    @GetMapping(path = "find")
    public Department findDepartment(@RequestParam String address) {
        return service.findDepartmentByAddress(address);
    }

    @GetMapping(path = "findall")
    public List<Department> findAll() {
        return service.findAllDepartments();
    }

    @DeleteMapping
    public void deleteDepartment(@RequestParam String address) {
        service.deleteDepartmentByAddress(address);
    }
}
