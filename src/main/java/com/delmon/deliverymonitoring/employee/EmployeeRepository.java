package com.delmon.deliverymonitoring.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findAppUserByPhoneNumber(String phoneNumber);
    boolean existByPhoneNumber(String phoneNumber);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Employee e " +
            "SET e.enabled = ?2 " +
            "WHERE e.phoneNumber = ?1")
    int updateEnabledByPhoneNumber(String phoneNumber, boolean enabled);
}
