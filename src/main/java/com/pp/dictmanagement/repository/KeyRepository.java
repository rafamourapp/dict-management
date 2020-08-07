package com.pp.dictmanagement.repository;

import com.pp.dictmanagement.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KeyRepository extends JpaRepository<Key, UUID> {

    List<Key> findAllByUserId(UUID id);

    @Query("SELECT k FROM Key k WHERE k.userId = ?1 and (k.status = 'ACTIVE' or k.status = 'CLAIMED')")
    List<Key> findAllActivatedByUser(String id);

    @Query("SELECT k FROM Key k WHERE k.keyValue = :value and (k.status = 'ACTIVE' or k.status = 'CLAIMED')")
    Optional<Key> findByKeyValue(@Param("value") String keyValue);

}
