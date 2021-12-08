package com.delmon.deliverymonitoring.ordering;

import com.delmon.deliverymonitoring.department.DepartmentRepository;
import com.delmon.deliverymonitoring.employee.EmployeeRepository;
import com.delmon.deliverymonitoring.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderingService {
    private final OrderingRepository repository;
    private final ProductRepository productRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    private final static String UUID_IS_EXISTING_MESSAGE = "Ordering with %s uuid is already existing";
    private final static String UUID_IS_NOT_EXISTING_MESSAGE = "Ordering with %s uuid isn't existing";

    public void saveNewOrdering(OrderingRequest orderingRequest) {
        // Checking if ordering uuid is unique
        if (repository.existsByUuid(orderingRequest.getUuid())) {
            throw new IllegalArgumentException(String.format(UUID_IS_EXISTING_MESSAGE, orderingRequest.getUuid()));
        }

        Ordering newOrdering = new Ordering(
                orderingRequest.getUuid(),
                productRepository.findAll().stream()
                        .filter(product -> orderingRequest.getProductIds().stream()
                                .anyMatch(aLong -> product.getId() == aLong))
                        .collect(Collectors.toList()),
                employeeRepository.getById(orderingRequest.getEmployeeId()),
                departmentRepository.getById(orderingRequest.getDepartmentId()),
                orderingRequest.getDateTime());

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
