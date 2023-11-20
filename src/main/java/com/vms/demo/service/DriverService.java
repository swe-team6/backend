package com.vms.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.DriverCreateDTO;
import com.vms.demo.dto.DriverDTO;
import com.vms.demo.entity.DriverEntity;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.DriverRepository;
import com.vms.demo.repository.UserRepository;
import com.vms.demo.types.RoleType;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<DriverDTO> getAllDrivers() {
        List<DriverEntity> drivers = driverRepository.findAll();
        return modelMapper.map(drivers, new TypeToken<List<DriverDTO>>() {
        }.getType());
    }

    public DriverDTO getDriverById(Long driverId) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(driverId);

        if (driverOptional.isPresent()) {
            DriverEntity driver = driverOptional.get();
            // UserEntity user = driver.getUser();
            DriverDTO driverDTO = modelMapper.map(driver, DriverDTO.class);
            // Map entities to DTO
            return driverDTO;
        } else {
            throw new EntityNotFoundException("Driver not found with id: " + driverId);
        }
    }

    public DriverCreateDTO createDriver(DriverCreateDTO driverCreateDTO) {
        UserEntity user = modelMapper.map(driverCreateDTO, UserEntity.class);
        System.out.println(user);
        user.setRole(RoleType.DRIVER);
        // System.out.println(e);
        // System.out.println(u);
        DriverEntity driver = user.getDriver();
        user.setDriver(null);
        user = userRepository.save(user);
        driver.setUser(user);
        driver = driverRepository.save(driver);
        DriverCreateDTO dto = modelMapper.map(user, DriverCreateDTO.class);
        return dto;
    }
}