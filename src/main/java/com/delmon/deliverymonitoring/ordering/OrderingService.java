package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.DepartmentRepository;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderingService {
    private final OrderingRepository repository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    private final static String UUID_IS_EXISTING_MESSAGE = "Ordering with %s uuid is already existing";
    private final static String UUID_IS_NOT_EXISTING_MESSAGE = "Ordering with %s uuid isn't existing";

    public void saveNewOrdering(OrderingRequest orderingRequest) {
        Ordering newOrdering = new Ordering(
                orderingRequest.getProductUnitList(),
                employeeRepository.getByUuid(orderingRequest.getEmployeeUuid()),
                departmentRepository.getById(orderingRequest.getDepartmentUuid()),
                LocalDateTime.now());

        repository.save(newOrdering);
    }

    public Ordering findOrderingByUuid(String uuid) {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException(String.format(UUID_IS_NOT_EXISTING_MESSAGE, uuid)));
    }

    public List<Ordering> findAllOrdering() {
        return repository.findAll();
    }

    public void deleteOrderingByUuid(String uuid) {
        // Checking if ordering uuid is existing
        if (!repository.existsByUuid(uuid)) {
            throw new IllegalArgumentException(String.format(UUID_IS_NOT_EXISTING_MESSAGE, uuid));
        }

        repository.deleteByUuid(uuid);
    }
}
