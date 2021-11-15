package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.model.WarehouseWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseWorkerRepository extends JpaRepository<WarehouseWorker, Long> { }
