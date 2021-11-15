package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> { }
