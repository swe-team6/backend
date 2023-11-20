package com.vms.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vms.demo.dto.car.CarCreateDTO;
import com.vms.demo.dto.car.CarDTO;
import com.vms.demo.entity.CarEntity;
import com.vms.demo.entity.DriverEntity;
import com.vms.demo.repository.CarRepository;
import com.vms.demo.repository.DriverRepository;
import com.vms.demo.types.CarStatus;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverRepository driverRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<CarDTO> getAllCars() {
        List<CarEntity> drivers = carRepository.findAll();
        return modelMapper.map(drivers, new TypeToken<List<CarDTO>>() {
        }.getType());
    }

    public CarDTO getCarById(Long carID) {
        Optional<CarEntity> carOptional = carRepository.findById(carID);

        if (carOptional.isPresent()) {
            CarEntity car = carOptional.get();
            // UserEntity user = car.getUser();
            CarDTO carDTO = modelMapper.map(car, CarDTO.class);
            // Map entities to DTO
            return carDTO;
        } else {
            throw new EntityNotFoundException("Car not found with id: " + carID);
        }
    }

    public CarCreateDTO createCar(CarCreateDTO driverCreateDTO) {
        CarEntity e = modelMapper.map(driverCreateDTO, CarEntity.class);
        e.setStatus(CarStatus.INACTIVE);
        e = carRepository.save(e);
        CarCreateDTO dto = modelMapper.map(e, CarCreateDTO.class);
        return dto;
    }

    public CarDTO assignDriver(Long driverID, Long carID) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverID);
        CarEntity car = carRepository.findByCarID(carID);
        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Car not found with id: " + driverID);
        }
        DriverEntity driver = driverOptional.get();
        if (driver.getCar() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Requested driver is already assigned to a car.");
        }

        car.setDriver(driver);
        car = carRepository.save(car);
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return carDTO;
    }
}