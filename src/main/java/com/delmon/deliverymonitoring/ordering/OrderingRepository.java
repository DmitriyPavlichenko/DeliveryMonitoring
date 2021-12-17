package com.delmon.deliverymonitoring.ordering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, String> {
    boolean existsByUuid(String uuid);

    Optional<Ordering> findByUuid(String uuid);

    void deleteByUuid(String uuid);

    //TODO optimize overriding
    @Transactional
    @Override
    <S extends Ordering> S save(S entity);
}
