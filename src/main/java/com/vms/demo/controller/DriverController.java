package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.DriverCreateDTO;
import com.vms.demo.dto.DriverDTO;
import com.vms.demo.service.DriverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping
    public List<DriverDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("{driverID}")
    public DriverDTO retrieve(@PathVariable Long driverID) {
        return driverService.getDriverById(driverID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverCreateDTO create(@Valid @RequestBody DriverCreateDTO driverCreateDTO) {
        return driverService.createDriver(driverCreateDTO);
    }
}
