package com.delmon.deliverymonitoring.service;

import com.delmon.deliverymonitoring.model.WarehouseWorker;
import com.delmon.deliverymonitoring.repository.WarehouseWorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WarehouseWorkerService {
    private final WarehouseWorkerRepository repository;

    private final static String NUMBER_IS_EXISTING_MESSAGE = "Worker with %s phone number is already existing";
    private final static String NUMBER_IS_NOT_EXISTING_MESSAGE = "Worker with %s phone number isn't existing";

    public void saveNewWarehouseWorker(WarehouseWorker worker) {
        // Checking if given worker's phone number is unique
        if (repository.existsByPhoneNumber(worker.getPhoneNumber())) {
            throw new IllegalStateException(String.format(NUMBER_IS_EXISTING_MESSAGE, worker.getPhoneNumber()));
        }

        // TODO: phone number validation

        repository.save(worker);
    }

    public WarehouseWorker findWarehouseWorkerByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalStateException(String.format(NUMBER_IS_NOT_EXISTING_MESSAGE, phoneNumber)));
    }

    public List<WarehouseWorker> findAllWarehouseWorkers() {
        return repository.findAll();
    }

    public void deleteWarehouseWorkerByPhoneNumber(String phoneNumber) {
        // Checking if given phone number is existing
        if (!repository.existsByPhoneNumber(phoneNumber)) {
            throw new IllegalStateException(String.format(NUMBER_IS_NOT_EXISTING_MESSAGE, phoneNumber));
        }

        repository.deleteByPhoneNumber(phoneNumber);
    }
}
