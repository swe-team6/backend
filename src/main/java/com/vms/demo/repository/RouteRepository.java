package com.vms.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.RouteEntity;
import com.vms.demo.types.RouteStatus;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    RouteEntity findByRouteID(Long routeID);

    List<RouteEntity> findByDriver_UserIDAndStatus(Long driverID, RouteStatus status, Sort sort);
}
