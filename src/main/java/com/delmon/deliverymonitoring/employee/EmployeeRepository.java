package com.delmon.deliverymonitoring.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findEmployeeByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    @Transactional
    void deleteByPhoneNumber(String phoneNumber);

    Employee getByUuid(String employeeUuid);

    Optional<Employee> findByUuid(String employeeUuid);
}
