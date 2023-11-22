package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.driver.DriverCreateDTO;
import com.vms.demo.dto.driver.DriverFullDTO;
import com.vms.demo.dto.driver.DriverUpdateDTO;
import com.vms.demo.dto.route.RouteDTO;
import com.vms.demo.service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping
    public List<DriverFullDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("{driverID}")
    public DriverFullDTO retrieve(@PathVariable Long driverID) {
        return driverService.getDriverById(driverID);
    }

    @GetMapping("{driverID}/routes")
    public List<RouteDTO> listRoutes(@PathVariable Long driverID) {
        return driverService.getDriverRoutes(driverID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverCreateDTO create(@Valid @RequestBody DriverCreateDTO driverCreateDTO) {
        return driverService.createDriver(driverCreateDTO);
    }

    @PutMapping("{driverID}")
    @ResponseStatus(HttpStatus.OK)
    public DriverFullDTO update(@PathVariable Long driverID, @RequestBody DriverUpdateDTO driverUpdateDTO) {
        return driverService.updateDriver(driverID, driverUpdateDTO);
    }

    @PostMapping("{driverID}/assignCar")
    @ResponseStatus(HttpStatus.CREATED)
    public DriverFullDTO assignCar(@PathVariable Long driverID, Long carID) {
        return driverService.assignCar(driverID, carID);
    }
}
