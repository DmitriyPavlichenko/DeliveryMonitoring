package com.delmon.deliverymonitoring.service;

import com.delmon.deliverymonitoring.exception.IllegalPhoneNumberException;
import com.delmon.deliverymonitoring.model.WarehouseWorker;
import com.delmon.deliverymonitoring.repository.WarehouseWorkerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WarehouseWorkerService {
    private final WarehouseWorkerRepository repository;
    // TODO: exception's messages move to constant fields

    public void saveNewWarehouseWorker(WarehouseWorker worker) {
        // Checking if given worker's phone number is unique
        if (repository.existsByPhoneNumber(worker.getPhoneNumber())) {
            throw new IllegalPhoneNumberException("Warehouse worker with " + worker.getPhoneNumber() + " is already existing");
        }

        // TODO: phone number validation

        repository.save(worker);
    }

    public WarehouseWorker findWarehouseWorkerByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalPhoneNumberException("Warehouse worker with " + phoneNumber + " isn't existing"));
    }

    public List<WarehouseWorker> findAllWarehouseWorkers() {
        return repository.findAll();
    }

    public void deleteWarehouseWorkerByPhoneNumber(String phoneNumber) {
        // Checking if given phone number is existing
        if (!repository.existsByPhoneNumber(phoneNumber)) {
            throw new IllegalPhoneNumberException("Warehouse worker with " + phoneNumber + " is already existing");
        }

        repository.deleteByPhoneNumber(phoneNumber);
    }
}
