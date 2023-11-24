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

import com.vms.demo.dto.fueler.FuelerCreateDTO;
import com.vms.demo.dto.fueler.FuelerDTO;
import com.vms.demo.dto.fueler.FuelerFullDTO;
import com.vms.demo.dto.fueler.FuelerUpdateDTO;
import com.vms.demo.service.FuelerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("fuelers")
public class FuelerController {
    @Autowired
    private FuelerService fuelerService;

    @GetMapping
    public List<FuelerFullDTO> getAllFuelers() {
        return fuelerService.getAllFuelers();
    }

    @GetMapping("{fuelerID}")
    public FuelerFullDTO retrieve(@PathVariable Long fuelerID) {
        return fuelerService.getFuelerById(fuelerID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuelerFullDTO create(@Valid @RequestBody FuelerCreateDTO fuelerCreateDTO) {
        return fuelerService.createFueler(fuelerCreateDTO);
    }

    @PutMapping("{fuelerID}")
    public FuelerDTO update(@PathVariable Long fuelerID,
            @RequestBody FuelerUpdateDTO fuelerUpdateDTO) {
        return fuelerService.updateFueler(fuelerID, fuelerUpdateDTO);
    }

    @DeleteMapping("{fuelerID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long fuelerID) {
        fuelerService.deleteFueler(fuelerID);
    }
}
