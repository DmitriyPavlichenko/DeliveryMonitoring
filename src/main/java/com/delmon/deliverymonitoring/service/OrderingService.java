package com.delmon.deliverymonitoring.service;

import com.delmon.deliverymonitoring.model.Ordering;
import com.delmon.deliverymonitoring.repository.OrderingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderingService {
    private final OrderingRepository repository;

    public String saveNewOrdering(Ordering ordering) {
        // Checking if ordering uuid is unique
        if (repository.existsByUuid(ordering.getUuid())) {
            throw new IllegalArgumentException("Ordering with " + ordering.getUuid() + " is already existing");
        }

        String uuid = UUID.randomUUID().toString();
        ordering.setUuid(uuid);
        repository.save(ordering);

        return uuid;
    }

    public Ordering findOrderingByUuid(String uuid) {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Ordering with " + uuid + " isn't existing"));
    }

    public List<Ordering> findAllOrdering() {
        return repository.findAll();
    }

    public void deleteOrderingByUuid(String uuid) {
        // Checking if ordering uuid is existing
        if (!repository.existsByUuid(uuid)) {
            throw new IllegalArgumentException("Ordering with " + uuid + " isn't existing");
        }

        repository.deleteByUuid(uuid);
    }
}
