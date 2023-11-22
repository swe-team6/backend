package com.vms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.MainJobEntity;

@Repository
public interface MainJobRepository extends JpaRepository<MainJobEntity, Long> {
}
