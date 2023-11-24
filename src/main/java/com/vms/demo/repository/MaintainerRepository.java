package com.vms.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vms.demo.entity.UserEntity;
import com.vms.demo.types.RoleType;

@Repository
public interface MaintainerRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUserIDAndRole(Long userID, RoleType role);

    List<UserEntity> findByRoleOrderByUserIDAsc(RoleType role);

    @Transactional
    Long deleteByUserIDAndRole(Long userID, RoleType role);
}
