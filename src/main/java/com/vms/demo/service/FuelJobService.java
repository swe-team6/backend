package com.vms.demo.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vms.demo.dto.fuelingJob.FuelJobCreateDTO;
import com.vms.demo.dto.fuelingJob.FuelJobFullDTO;
import com.vms.demo.dto.fuelingJob.FuelJobUpdateDTO;
import com.vms.demo.entity.CarEntity;
import com.vms.demo.entity.DriverEntity;
import com.vms.demo.entity.DriverHistoryEntity;
import com.vms.demo.entity.FuelJobEntity;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.CarRepository;
import com.vms.demo.repository.DriverRepository;
import com.vms.demo.repository.FuelJobRepository;
import com.vms.demo.repository.UserRepository;
import com.vms.demo.types.RoleType;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FuelJobService {

    @Autowired
    private FuelJobRepository fuelJobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private DriverHistoryService driverHistoryService;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<FuelJobFullDTO> getAllFuelJobs() {
        List<FuelJobEntity> fuelJobs = fuelJobRepository.findAll(Sort.by(Sort.Direction.ASC, "dateTime"));
        return modelMapper.map(fuelJobs, new TypeToken<List<FuelJobFullDTO>>() {
        }.getType());
    }

    public FuelJobFullDTO getFuelJobById(Long fuelJobID) {
        Optional<FuelJobEntity> fuelJobOptional = fuelJobRepository.findById(fuelJobID);
        if (fuelJobOptional.isEmpty()) {
            throw new EntityNotFoundException("FuelJob not found with id: " + fuelJobID);
        }
        FuelJobEntity fuelJob = fuelJobOptional.get();
        FuelJobFullDTO fuelJobDTO = modelMapper.map(fuelJob, FuelJobFullDTO.class);
        return fuelJobDTO;
    }

    public FuelJobCreateDTO createFuelJob(FuelJobCreateDTO fuelJobCreateDTO) {
        Optional<UserEntity> userOptional = userRepository.findById(fuelJobCreateDTO.getFuelerUserID());
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("Invalid fueler id (not found): " + fuelJobCreateDTO.getFuelerUserID());
        }
        UserEntity user = userOptional.get();
        if (user.getRole() != RoleType.FUELER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Requested user is not a maintainer.");
        }
        Optional<CarEntity> carOptional = carRepository.findById(fuelJobCreateDTO.getCarID());
        if (carOptional.isEmpty()) {
            throw new EntityNotFoundException("Invalid car id (not found): " + fuelJobCreateDTO.getCarID());
        }
        CarEntity car = carOptional.get();
        Optional<DriverEntity> driverOptional = driverRepository.findById(fuelJobCreateDTO.getDriverID());
        if (driverOptional.isEmpty()) {
            throw new EntityNotFoundException("Invalid driver id (not found): " + fuelJobCreateDTO.getDriverID());
        }
        DriverEntity driver = driverOptional.get();
        FuelJobEntity fuelJob = modelMapper.map(fuelJobCreateDTO, FuelJobEntity.class);
        fuelJob.setDateTime(ZonedDateTime.now());
        fuelJob.setCar(car);
        fuelJob.setFueler(user);
        fuelJob.setDriver(driver);
        fuelJob = fuelJobRepository.save(fuelJob);

        if (car.getDriver() != null) {
            DriverHistoryEntity history = driverHistoryService.getEntity(car.getDriver(), car);
            history.setFuelConsumption(history.getFuelConsumption() + fuelJob.getFuelAmount());
        }
        return modelMapper.map(fuelJob, FuelJobCreateDTO.class);
    }

    public FuelJobFullDTO updateFuelJob(Long fuelJobID, FuelJobUpdateDTO fuelJobUpdateDTO) {
        Optional<FuelJobEntity> fuelJobOptional = fuelJobRepository.findById(fuelJobID);
        if (fuelJobOptional.isEmpty()) {
            throw new EntityNotFoundException("FuelJob not found with id: " + fuelJobID);
        }
        FuelJobEntity fuelJob = fuelJobOptional.get();
        if (fuelJobUpdateDTO.getFuelAmount() != 0) {
            fuelJob.setFuelAmount(fuelJobUpdateDTO.getFuelAmount());
        }
        if (fuelJobUpdateDTO.getFuelCost() != 0) {
            fuelJob.setFuelCost(fuelJobUpdateDTO.getFuelCost());
        }
        if (fuelJobUpdateDTO.getStationName() != null) {
            fuelJob.setStationName(fuelJobUpdateDTO.getStationName());
        }
        if (fuelJobUpdateDTO.getMileageBefore() != 0) {
            fuelJob.setMileageBefore(fuelJobUpdateDTO.getMileageBefore());
        }
        if (fuelJobUpdateDTO.getMileageAfter() != 0) {
            fuelJob.setMileageAfter(fuelJobUpdateDTO.getMileageAfter());
        }
        if (fuelJobUpdateDTO.getLitersBefore() != 0) {
            fuelJob.setLitersBefore(fuelJobUpdateDTO.getLitersBefore());
        }
        if (fuelJobUpdateDTO.getLitersAfter() != 0) {
            fuelJob.setLitersAfter(fuelJobUpdateDTO.getLitersAfter());
        }
        fuelJob = fuelJobRepository.save(fuelJob);
        return modelMapper.map(fuelJob, FuelJobFullDTO.class);
    }

    public void deleteFuelJob(Long fuelJobID) {
        fuelJobRepository.deleteById(fuelJobID);
    }
}