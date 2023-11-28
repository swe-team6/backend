package com.vms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.BiddingEntity;

@Repository
public interface BiddingRepository extends JpaRepository<BiddingEntity, Long> {
}
