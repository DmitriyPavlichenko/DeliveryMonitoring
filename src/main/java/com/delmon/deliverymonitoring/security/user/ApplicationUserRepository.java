package com.delmon.deliverymonitoring.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findAppUserByEmployee_PhoneNumber(String phoneNumber);
    boolean existsByEmployee_PhoneNumber(String phoneNumber);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ApplicationUser u " +
            "SET u.enabled = ?2 " +
            "WHERE u.employee.phoneNumber = ?1")
    int updateEnabledByPhoneNumber(String email, boolean enabled);

    void deleteByEmployee_PhoneNumber(String phoneNumber);
}
