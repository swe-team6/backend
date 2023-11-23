package com.vms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.UserEntity;

@Repository
public interface FuelerRepository extends JpaRepository<UserEntity, Long> {
}
