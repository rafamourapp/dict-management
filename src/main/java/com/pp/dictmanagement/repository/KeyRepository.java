package com.pp.dictmanagement.repository;

import com.pp.dictmanagement.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KeyRepository extends JpaRepository<Key, UUID> {

    List<Key> findAllByUserId(UUID id);

    @Query("SELECT k FROM Key k WHERE k.userId = 1? and (k.status like ACTIVE or CLAIMED)")
    List<Key> findAllActivatedByUser(UUID id);

    @Query("SELECT k FROM Key k WHERE k.value = 1? and (k.status like ACTIVE or CLAIMED)")
    Optional<Key> findByKeyValue(String keyValue);

}
