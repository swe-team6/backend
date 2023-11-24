package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.fuelingJob.FuelJobCreateDTO;
import com.vms.demo.dto.fuelingJob.FuelJobFullDTO;
import com.vms.demo.dto.fuelingJob.FuelJobUpdateDTO;
import com.vms.demo.service.FuelJobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("fuelJobs")
public class FuelJobController {
    @Autowired
    private FuelJobService fuelJobService;

    @GetMapping
    public List<FuelJobFullDTO> getAllFuelJobs() {
        return fuelJobService.getAllFuelJobs();
    }

    @GetMapping("{fuelJobID}")
    public FuelJobFullDTO retrieve(@PathVariable Long fuelJobID) {
        return fuelJobService.getFuelJobById(fuelJobID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuelJobCreateDTO create(@Valid @RequestBody FuelJobCreateDTO fuelJobCreateDTO) {
        return fuelJobService.createFuelJob(fuelJobCreateDTO);
    }

    @PutMapping("{fuelJobID}")
    @ResponseStatus(HttpStatus.OK)
    public FuelJobFullDTO update(@PathVariable Long fuelJobID, @RequestBody FuelJobUpdateDTO fuelJobUpdateDTO) {
        return fuelJobService.updateFuelJob(fuelJobID, fuelJobUpdateDTO);
    }

    @DeleteMapping("{fuelJobID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long fuelJobID) {
        fuelJobService.deleteFuelJob(fuelJobID);
    }
}
