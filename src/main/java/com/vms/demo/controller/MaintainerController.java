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

import com.vms.demo.dto.maintainer.MaintainerCreateDTO;
import com.vms.demo.dto.maintainer.MaintainerDTO;
import com.vms.demo.dto.maintainer.MaintainerFullDTO;
import com.vms.demo.dto.maintainer.MaintainerUpdateDTO;
import com.vms.demo.service.MaintainerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("maintainers")
public class MaintainerController {
    @Autowired
    private MaintainerService maintainerService;

    @GetMapping
    public List<MaintainerFullDTO> getAllMaintainers() {
        return maintainerService.getAllMaintainers();
    }

    @GetMapping("{maintainerID}")
    public MaintainerFullDTO retrieve(@PathVariable Long maintainerID) {
        return maintainerService.getMaintainerById(maintainerID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaintainerFullDTO create(@Valid @RequestBody MaintainerCreateDTO maintainerCreateDTO) {
        return maintainerService.createMaintainer(maintainerCreateDTO);
    }

    @PutMapping("{maintainerID}")
    public MaintainerDTO update(@PathVariable Long maintainerID,
            @RequestBody MaintainerUpdateDTO maintainerUpdateDTO) {
        return maintainerService.updateMaintainer(maintainerID, maintainerUpdateDTO);
    }

    @DeleteMapping("{maintainerID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long maintainerID) {
        maintainerService.deleteMaintainer(maintainerID);
    }
}
