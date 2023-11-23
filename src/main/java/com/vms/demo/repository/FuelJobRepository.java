package com.vms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.FuelJobEntity;

@Repository
public interface FuelJobRepository extends JpaRepository<FuelJobEntity, Long> {
}
