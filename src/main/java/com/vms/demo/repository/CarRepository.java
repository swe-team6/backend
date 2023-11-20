package com.vms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    CarEntity findByCarID(Long carID);
}
