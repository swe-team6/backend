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

import com.vms.demo.dto.car.CarCreateDTO;
import com.vms.demo.dto.car.CarUpdateDTO;
import com.vms.demo.dto.car.CarWithDriverDTO;
import com.vms.demo.service.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public List<CarWithDriverDTO> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("{carID}")
    public CarWithDriverDTO retrieve(@PathVariable Long carID) {
        return carService.getCarById(carID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarWithDriverDTO create(@Valid @RequestBody CarCreateDTO carCreateDTO) {
        return carService.createCar(carCreateDTO);
    }

    @PutMapping("{carID}")
    @ResponseStatus(HttpStatus.OK)
    public CarWithDriverDTO update(@PathVariable Long carID, @RequestBody CarUpdateDTO carUpdateDTO) {
        return carService.updateCar(carID, carUpdateDTO);
    }

    @DeleteMapping("{carID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long carID) {
        carService.deleteCar(carID);
    }
}
