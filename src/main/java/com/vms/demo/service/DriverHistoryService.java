package com.vms.demo.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.driverHistory.DriverHistoryFullDTO;
import com.vms.demo.entity.CarEntity;
import com.vms.demo.entity.DriverEntity;
import com.vms.demo.entity.DriverHistoryEntity;
import com.vms.demo.repository.DriverHistoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverHistoryService {

    @Autowired
    private DriverHistoryRepository driverHistoryRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public DriverHistoryEntity getEntityByDriverIDCarID(Long driverID, Long carID) {
        List<DriverHistoryEntity> list = driverHistoryRepository.findByDriverIDCarID(driverID, carID);
        if (list.size() == 0) {
            throw new EntityNotFoundException(
                    "DriverHistory not found for driverID: " + driverID + " and carID: " + carID);
        }
        return list.get(0);
    }

    public DriverHistoryEntity getEntity(DriverEntity driver, CarEntity car) {
        List<DriverHistoryEntity> list = driverHistoryRepository.findByDriverAndCarAndRemovedDateIsNull(driver, car);
        if (list.size() == 0) {
            throw new EntityNotFoundException(
                    "DriverHistory not found for driver: " + driver.getUserID() + " and car: " + car.getCarID());
        }
        return list.get(0);
    }

    public DriverHistoryEntity createEntity(DriverEntity driver, CarEntity car) {
        DriverHistoryEntity e = new DriverHistoryEntity();
        e.setAssignedDate(ZonedDateTime.now());
        e.setRemovedDate(null);
        e.setCar(car);
        e.setDriver(driver);
        e.setFuelConsumption(0);
        e.setMaintenanceCost(0);
        return driverHistoryRepository.save(e);
    }

    public List<DriverHistoryFullDTO> getAllDriverHistorys() {
        List<DriverHistoryEntity> driverHistorys = driverHistoryRepository
                .findAll(Sort.by(Sort.Direction.DESC, "assignedDate"));
        return modelMapper.map(driverHistorys, new TypeToken<List<DriverHistoryFullDTO>>() {
        }.getType());
    }

    public List<DriverHistoryFullDTO> getDriverHistoryByDriverId(Long driverID) {
        List<DriverHistoryEntity> driverHistorys = driverHistoryRepository
                .findByDriver_UserID(driverID);
        return modelMapper.map(driverHistorys, new TypeToken<List<DriverHistoryFullDTO>>() {
        }.getType());
    }

    public DriverHistoryEntity save(DriverHistoryEntity driverHistoryEntity) {
        return driverHistoryRepository.save(driverHistoryEntity);
    }

    public DriverHistoryEntity setRemovedDate(DriverEntity driver, CarEntity car) {
        DriverHistoryEntity history;
        try {
            history = this.getEntity(driver, car);
        } catch (EntityNotFoundException e) {
            history = this.createEntity(driver, car);
        }
        history.setRemovedDate(ZonedDateTime.now());
        return this.save(history);
    }
}