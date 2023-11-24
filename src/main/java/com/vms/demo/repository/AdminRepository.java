package com.vms.demo.repository;

import com.vms.demo.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    Optional<AdminEntity> findByAdminID(Long adminID);

    Optional<AdminEntity> findByEmail(String email);
    @Transactional
    void deleteByAdminID(Long adminID);
}
