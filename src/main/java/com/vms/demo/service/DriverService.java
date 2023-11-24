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

import com.vms.demo.dto.driver.DriverCreateDTO;
import com.vms.demo.dto.driver.DriverFullDTO;
import com.vms.demo.dto.driver.DriverUpdateDTO;
import com.vms.demo.dto.route.RouteDTO;
import com.vms.demo.entity.CarEntity;
import com.vms.demo.entity.DriverEntity;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.CarRepository;
import com.vms.demo.repository.DriverRepository;
import com.vms.demo.repository.UserRepository;
import com.vms.demo.types.RoleType;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<DriverFullDTO> getAllDrivers() {
        List<DriverEntity> drivers = driverRepository.findAll(Sort.by(Sort.Direction.ASC, "userID"));
        return modelMapper.map(drivers, new TypeToken<List<DriverFullDTO>>() {
        }.getType());
    }

    public void deleteDriverById(Long driverID) {
        driverRepository.deleteById(driverID);
    }

    public DriverFullDTO getDriverById(Long driverID) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverID);

        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Driver not found with id: " + driverID);
        }
        DriverEntity driver = driverOptional.get();
        DriverFullDTO driverDTO = modelMapper.map(driver, DriverFullDTO.class);
        return driverDTO;
    }

    public List<RouteDTO> getDriverRoutes(Long driverID) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverID);
        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Driver not found with id: " + driverID);
        }
        DriverEntity driver = driverOptional.get();
        return modelMapper.map(driver.getRoutes(), new TypeToken<List<RouteDTO>>() {
        }.getType());
    }

    public DriverCreateDTO createDriver(DriverCreateDTO driverCreateDTO) {
        UserEntity user = modelMapper.map(driverCreateDTO, UserEntity.class);
        user.setRole(RoleType.DRIVER);
        DriverEntity driver = user.getDriver();
        user.setDriver(null);
        user = userRepository.save(user);
        driver.setUser(user);
        driver = driverRepository.save(driver);
        DriverCreateDTO dto = modelMapper.map(user, DriverCreateDTO.class);
        return dto;
    }

    public DriverFullDTO assignCar(Long driverID, Long carID) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverID);
        Optional<CarEntity> carOptional = carRepository.findById(carID);
        if (!carOptional.isPresent()) {
            throw new EntityNotFoundException("Car not found with id: " + carID);
        }
        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Driver not found with id: " + driverID);
        }
        CarEntity car = carOptional.get();
        DriverEntity driver = driverOptional.get();
        // if (driver.getCar() != null) {
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
        // "Requested driver is already assigned to a car.");
        // }

        driver.setCar(car);
        driver = driverRepository.save(driver);
        DriverFullDTO driverDTO = modelMapper.map(driver, DriverFullDTO.class);
        return driverDTO;
    }

    public DriverFullDTO unassignCar(Long driverID) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverID);
        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Driver not found with id: " + driverID);
        }
        DriverEntity driver = driverOptional.get();
        driver.setCar(null);
        DriverFullDTO driverDTO = modelMapper.map(driver, DriverFullDTO.class);
        return driverDTO;
    }

    public DriverFullDTO updateDriver(Long driverID, DriverUpdateDTO driverUpdateDTO) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverID);
        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Driver not found with id: " + driverID);
        }
        DriverEntity driver = driverOptional.get();
        UserEntity user = driver.getUser();

        if (driverUpdateDTO.getDrivingLicense() != null) {
            driver.setDrivingLicense(driverUpdateDTO.getDrivingLicense());
        }
        if (driverUpdateDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(driverUpdateDTO.getPhoneNumber());
        }
        if (driverUpdateDTO.getEmail() != null) {
            user.setEmail(driverUpdateDTO.getEmail());
        }
        if (driverUpdateDTO.getAddress() != null) {
            user.setAddress(driverUpdateDTO.getAddress());
        }
        if (driverUpdateDTO.getFirstName() != null) {
            user.setFirstName(driverUpdateDTO.getFirstName());
        }
        if (driverUpdateDTO.getMiddleName() != null) {
            user.setMiddleName(driverUpdateDTO.getMiddleName());
        }
        if (driverUpdateDTO.getLastName() != null) {
            user.setLastName(driverUpdateDTO.getLastName());
        }
        if (driverUpdateDTO.getGovID() != null) {
            user.setGovID(driverUpdateDTO.getGovID());
        }
        if (driverUpdateDTO.getPictureUrl() != null) {
            user.setPictureUrl(driverUpdateDTO.getPictureUrl());
        }
        // TODO: Hash password
        if (driverUpdateDTO.getPassword() != null) {
            user.setPassword(driverUpdateDTO.getPassword());
        }
        userRepository.save(user);
        driver = driverRepository.save(driver);
        DriverFullDTO driverDTO = modelMapper.map(driver, DriverFullDTO.class);
        return driverDTO;
    }
}