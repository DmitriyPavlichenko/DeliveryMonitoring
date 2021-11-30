package com.delmon.deliverymonitoring.temporary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseWorkerRepository extends JpaRepository<WarehouseWorker, Long> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<WarehouseWorker> findByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);
}
