package com.delmon.deliverymonitoring.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByAddress(String address);

    Optional<Department> findByAddress(String address);

    void deleteByAddress(String address);
}
