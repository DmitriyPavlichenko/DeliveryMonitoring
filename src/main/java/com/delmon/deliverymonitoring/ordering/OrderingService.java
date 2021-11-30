package com.delmon.deliverymonitoring.ordering;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrderingService {
    private final OrderingRepository repository;

    private final static String UUID_IS_EXISTING_MESSAGE = "Ordering with %s uuid is already existing";
    private final static String UUID_IS_NOT_EXISTING_MESSAGE = "Ordering with %s uuid isn't existing";

    public String saveNewOrdering(Ordering ordering) {
        // Checking if ordering uuid is unique
        if (repository.existsByUuid(ordering.getUuid())) {
            throw new IllegalArgumentException(String.format(UUID_IS_EXISTING_MESSAGE, ordering.getUuid()));
        }

        String uuid = UUID.randomUUID().toString();
        ordering.setUuid(uuid);
        repository.save(ordering);

        return uuid;
    }

    public Ordering findOrderingByUuid(String uuid) {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException(String.format(UUID_IS_NOT_EXISTING_MESSAGE, uuid)));
    }

    public List<Ordering> findAllOrdering() {
        return repository.findAll();
    }

    public void deleteOrderingByUuid(String uuid) {
        // Checking if ordering uuid is existing
        if (!repository.existsByUuid(uuid)) {
            throw new IllegalArgumentException(String.format(UUID_IS_NOT_EXISTING_MESSAGE, uuid));
        }

        repository.deleteByUuid(uuid);
    }
}
