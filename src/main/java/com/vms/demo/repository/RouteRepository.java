package com.vms.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.demo.entity.RouteEntity;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    RouteEntity findByRouteID(Long routeID);
}
