package com.vms.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.CarEntity;
import com.vms.demo.entity.DriverEntity;
import com.vms.demo.entity.DriverHistoryEntity;

@Repository
public interface DriverHistoryRepository extends JpaRepository<DriverHistoryEntity, Long> {
    @Query(value = "SELECT d FROM DriversHistory d WHERE d.driver_id = ?1 AND d.car_id = ?2 AND d.removedDate = NULL", nativeQuery = true)
    List<DriverHistoryEntity> findByDriverIDCarID(Long driverID, Long carID);

    List<DriverHistoryEntity> findByDriver_UserID(Long driverID);

    List<DriverHistoryEntity> findByDriverAndCarAndRemovedDateIsNull(DriverEntity driver, CarEntity car);
}
