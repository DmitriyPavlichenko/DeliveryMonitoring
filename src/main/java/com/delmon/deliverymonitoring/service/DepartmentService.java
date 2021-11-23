package com.delmon.deliverymonitoring.service;

import com.delmon.deliverymonitoring.model.Department;
import com.delmon.deliverymonitoring.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    private final static String ADDRESS_IS_EXISTING_MESSAGE = "Department with %s address is already existing";
    private final static String ADDRESS_IS_NOT_EXISTING_MESSAGE = "Department with %s address address isn't existing";

    public void saveNewDepartment(Department department) {
        // Checking if given department address is unique
        if (repository.existsByAddress(department.getAddress())) {
            throw new IllegalStateException(String.format(ADDRESS_IS_EXISTING_MESSAGE, department.getAddress()));
        }

        // TODO: address validation

        repository.save(department);
    }

    public Department findDepartmentByAddress(String address) {
        return repository.findByAddress(address)
                .orElseThrow(() -> new IllegalStateException(String.format(ADDRESS_IS_NOT_EXISTING_MESSAGE, address)));
    }

    public List<Department> findAllDepartments() {
        return repository.findAll();
    }

    public void deleteDepartmentByAddress(String address) {
        // Checking if given address is existing
        if (!repository.existsByAddress(address)) {
            throw new IllegalStateException(String.format(ADDRESS_IS_NOT_EXISTING_MESSAGE, address));
        }

        repository.deleteByAddress(address);
    }
}
