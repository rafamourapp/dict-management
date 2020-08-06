package com.pp.dictmanagement.repository;

import com.pp.dictmanagement.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KeyRepository extends JpaRepository<Key, UUID> {

    List<Key> findByUserId(UUID id);

}
