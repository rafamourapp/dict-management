package com.pp.dictmanagement.repository;

import com.pp.dictmanagement.entity.Key;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KeyRepository extends JpaRepository<Key, UUID> {

}
