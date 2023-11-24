package com.vms.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.car.CarCreateDTO;
import com.vms.demo.dto.car.CarUpdateDTO;
import com.vms.demo.dto.car.CarWithDriverDTO;
import com.vms.demo.entity.CarEntity;
import com.vms.demo.repository.CarRepository;
import com.vms.demo.types.CarStatus;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<CarWithDriverDTO> getAllCars() {
        List<CarEntity> cars = carRepository.findAll(Sort.by(Sort.Direction.ASC, "carID"));
        return modelMapper.map(cars, new TypeToken<List<CarWithDriverDTO>>() {
        }.getType());
    }

    public CarWithDriverDTO getCarById(Long carID) {
        Optional<CarEntity> carOptional = carRepository.findById(carID);

        if (carOptional.isPresent()) {
            CarEntity car = carOptional.get();
            // UserEntity user = car.getUser();
            CarWithDriverDTO carDTO = modelMapper.map(car, CarWithDriverDTO.class);
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

    public CarWithDriverDTO updateCar(Long carID, CarUpdateDTO carUpdateDTO) {
        Optional<CarEntity> carOptional = carRepository.findById(carID);
        if (!carOptional.isPresent()) {
            throw new EntityNotFoundException("Car not found with id: " + carID);
        }
        CarEntity car = carOptional.get();
        if (carUpdateDTO.getLicensePlate() != null) {
            car.setLicensePlate(carUpdateDTO.getLicensePlate());
        }
        if (carUpdateDTO.getModel() != null) {
            car.setModel(carUpdateDTO.getModel());
        }
        if (carUpdateDTO.getYear() != 0) {
            car.setYear(carUpdateDTO.getYear());
        }
        if (carUpdateDTO.getCapacity() != 0) {
            car.setCapacity(carUpdateDTO.getCapacity());
        }
        if (carUpdateDTO.getType() != null) {
            car.setType(carUpdateDTO.getType());
        }
        if (carUpdateDTO.getPictureUrl() != null) {
            car.setPictureUrl(carUpdateDTO.getPictureUrl());
        }
        if (carUpdateDTO.getMileage() != 0) {
            car.setMileage(carUpdateDTO.getMileage());
        }
        if (carUpdateDTO.getStatus() != null) {
            car.setStatus(carUpdateDTO.getStatus());
        }
        if (carUpdateDTO.getMileageInterval() != 0) {
            car.setMileageInterval(carUpdateDTO.getMileageInterval());
        }
        if (carUpdateDTO.getTimeInterval() != null) {
            car.setTimeInterval(carUpdateDTO.getTimeInterval());
        }
        if (carUpdateDTO.getMaintenanceJson() != null) {
            car.setMaintenanceJson(carUpdateDTO.getMaintenanceJson());
        }
        if (carUpdateDTO.getUsageDescription() != null) {
            car.setUsageDescription(carUpdateDTO.getUsageDescription());
        }
        car = carRepository.save(car);
        CarWithDriverDTO carDTO = modelMapper.map(car, CarWithDriverDTO.class);
        return carDTO;
    }
}