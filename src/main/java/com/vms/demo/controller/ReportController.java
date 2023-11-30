package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.driverHistory.DriverHistoryFullDTO;
import com.vms.demo.dto.route.RouteFullDTO;
import com.vms.demo.service.DriverHistoryService;
import com.vms.demo.service.RouteService;

@RestController
@RequestMapping("reports")
public class ReportController {
    @Autowired
    private RouteService routeService;

    @Autowired
    private DriverHistoryService driverHistoryService;

    @GetMapping("/{driverID}/routes")
    public List<RouteFullDTO> getAllRoutes(@PathVariable Long driverID) {
        return routeService.getCompletedRoutes(driverID);
    }

    @GetMapping("/{driverID}/histories")
    public List<DriverHistoryFullDTO> getAllDriverHistorysByDriverID(@PathVariable Long driverID) {
        return driverHistoryService.getDriverHistoryByDriverId(driverID);
    }

    @GetMapping("/driverHistories")
    public List<DriverHistoryFullDTO> getAllDriverHistorys() {
        return driverHistoryService.getAllDriverHistorys();
    }
}
