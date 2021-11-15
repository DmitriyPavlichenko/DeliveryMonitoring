package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Long> {
    boolean existsByUuid(String uuid);

    Optional<Ordering> findByUuid(String uuid);

    void deleteByUuid(String uuid);
}
