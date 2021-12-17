package com.delmon.deliverymonitoring.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByAddress(String address);

    Optional<Department> findByAddress(String address);

    @Transactional
    void deleteByAddress(String address);
}
