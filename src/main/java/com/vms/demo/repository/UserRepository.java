package com.vms.demo.repository;

import com.vms.demo.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserId(Long userID);
    @Transactional
    void deleteByUserID(Long userID);
}
