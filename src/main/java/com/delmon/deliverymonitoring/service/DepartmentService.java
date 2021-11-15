package com.delmon.deliverymonitoring.service;

import com.delmon.deliverymonitoring.exception.IllegalAddressException;
import com.delmon.deliverymonitoring.model.Department;
import com.delmon.deliverymonitoring.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    // TODO: exception's messages move to constant fields

    public void saveNewDepartment(Department department) {
        // Checking if given department address is unique
        if (repository.existsByAddress(department.getAddress())) {
            throw new IllegalAddressException("Department with " + department.getAddress() + " address is already existing");
        }

        // TODO: address validation

        repository.save(department);
    }

    public Department findDepartmentByAddress(String address) {
        return repository.findByAddress(address)
                .orElseThrow(() -> new IllegalAddressException("Department with " + address + " address isn't existing"));
    }

    public List<Department> findAllDepartments() {
        return repository.findAll();
    }

    public void deleteDepartmentByAddress(String address) {
        // Checking if given address is existing
        if (!repository.existsByAddress(address)) {
            throw new IllegalAddressException("Department with " + address + " address isn't existing");
        }

        repository.deleteByAddress(address);
    }
}
