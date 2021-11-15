package com.delmon.deliverymonitoring.repository;

import com.delmon.deliverymonitoring.model.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderingRepository extends JpaRepository<Ordering, Long> { }
