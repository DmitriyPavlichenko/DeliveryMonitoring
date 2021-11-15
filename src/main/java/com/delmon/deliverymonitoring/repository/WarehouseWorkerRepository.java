package com.delmon.deliverymonitoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseWorkerRepository extends JpaRepository<WarehouseWorkerRepository, Long> { }
