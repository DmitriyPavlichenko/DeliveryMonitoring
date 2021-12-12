package com.delmon.deliverymonitoring.department;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/supply/department")
public class DepartmentController {
    private DepartmentService service;

    @PostMapping
    @PreAuthorize("hasAuthority('department:save')")
    public void saveDepartment(@RequestBody Department department) {
        service.saveNewDepartment(department);
    }

    @GetMapping(path = "find")
    @PreAuthorize("hasAuthority('department:find')")
    public Department findDepartment(@RequestParam String address) {
        return service.findDepartmentByAddress(address);
    }

    @GetMapping(path = "findall")
    @PreAuthorize("hasAuthority('department:find')")
    public List<Department> findAll() {
        return service.findAllDepartments();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('department:delete')")
    public void deleteDepartment(@RequestParam String address) {
        service.deleteDepartmentByAddress(address);
    }
}
