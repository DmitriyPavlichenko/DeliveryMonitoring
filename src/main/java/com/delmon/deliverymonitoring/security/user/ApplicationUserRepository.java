package com.delmon.deliverymonitoring.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findAppUserByEmployee_PhoneNumber(String phoneNumber);

    boolean existsByEmployee_PhoneNumber(String phoneNumber);

    @Transactional
    void deleteByEmployee_PhoneNumber(String phoneNumber);
}
