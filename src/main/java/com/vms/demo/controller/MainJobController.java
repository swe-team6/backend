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

import com.vms.demo.dto.maintenanceJob.MainJobCreateDTO;
import com.vms.demo.dto.maintenanceJob.MainJobFullDTO;
import com.vms.demo.dto.maintenanceJob.MainJobUpdateDTO;
import com.vms.demo.service.MainJobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("mainJobs")
public class MainJobController {
    @Autowired
    private MainJobService mainJobService;

    @GetMapping
    public List<MainJobFullDTO> getAllMainJobs() {
        return mainJobService.getAllMainJobs();
    }

    @GetMapping("{mainJobID}")
    public MainJobFullDTO retrieve(@PathVariable Long mainJobID) {
        return mainJobService.getMainJobById(mainJobID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MainJobCreateDTO create(@Valid @RequestBody MainJobCreateDTO mainJobCreateDTO) {
        return mainJobService.createMainJob(mainJobCreateDTO);
    }

    @PutMapping("{mainJobID}")
    @ResponseStatus(HttpStatus.OK)
    public MainJobFullDTO update(@PathVariable Long mainJobID, @RequestBody MainJobUpdateDTO mainJobUpdateDTO) {
        return mainJobService.updateMainJob(mainJobID, mainJobUpdateDTO);
    }
}
