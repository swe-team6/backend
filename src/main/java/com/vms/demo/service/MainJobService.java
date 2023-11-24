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

import com.vms.demo.dto.maintenanceJob.MainJobCreateDTO;
import com.vms.demo.dto.maintenanceJob.MainJobFullDTO;
import com.vms.demo.dto.maintenanceJob.MainJobUpdateDTO;
import com.vms.demo.entity.CarEntity;
import com.vms.demo.entity.MainJobEntity;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.CarRepository;
import com.vms.demo.repository.MainJobRepository;
import com.vms.demo.repository.UserRepository;
import com.vms.demo.types.RoleType;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MainJobService {

    @Autowired
    private MainJobRepository mainJobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<MainJobFullDTO> getAllMainJobs() {
        List<MainJobEntity> mainJobs = mainJobRepository.findAll(Sort.by(Sort.Direction.ASC, "dateTime"));
        return modelMapper.map(mainJobs, new TypeToken<List<MainJobFullDTO>>() {
        }.getType());
    }

    public MainJobFullDTO getMainJobById(Long mainJobID) {
        Optional<MainJobEntity> mainJobOptional = mainJobRepository.findById(mainJobID);
        if (!mainJobOptional.isPresent()) {
            throw new EntityNotFoundException("MainJob not found with id: " + mainJobID);
        }
        MainJobEntity mainJob = mainJobOptional.get();
        MainJobFullDTO mainJobDTO = modelMapper.map(mainJob, MainJobFullDTO.class);
        return mainJobDTO;
    }

    public MainJobCreateDTO createMainJob(MainJobCreateDTO mainJobCreateDTO) {
        Optional<UserEntity> userOptional = userRepository.findById(mainJobCreateDTO.getMaintainerUserID());
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException(
                    "Invalid fueler id (not found): " + mainJobCreateDTO.getMaintainerUserID());
        }
        UserEntity user = userOptional.get();
        if (user.getRole() != RoleType.MAINTAINER) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Requested user is not a maintainer.");
        }
        Optional<CarEntity> carOptional = carRepository.findById(mainJobCreateDTO.getCarID());
        if (!carOptional.isPresent()) {
            throw new EntityNotFoundException("Invalid car id (not found): " + mainJobCreateDTO.getCarID());
        }
        CarEntity car = carOptional.get();

        MainJobEntity mainJob = modelMapper.map(mainJobCreateDTO, MainJobEntity.class);
        mainJob.setDateTime(ZonedDateTime.now());
        mainJob.setCar(car);
        mainJob.setMaintainer(user);

        mainJob = mainJobRepository.save(mainJob);
        MainJobCreateDTO dto = modelMapper.map(mainJob, MainJobCreateDTO.class);
        return dto;
    }

    public MainJobFullDTO updateMainJob(Long mainJobID, MainJobUpdateDTO mainJobUpdateDTO) {
        Optional<MainJobEntity> mainJobOptional = mainJobRepository.findById(mainJobID);
        if (!mainJobOptional.isPresent()) {
            throw new EntityNotFoundException("MainJob not found with id: " + mainJobID);
        }
        MainJobEntity mainJob = mainJobOptional.get();
        if (mainJobUpdateDTO.getDescription() != null) {
            mainJob.setDescription(mainJobUpdateDTO.getDescription());
        }
        if (mainJobUpdateDTO.getServiceType() != null) {
            mainJob.setServiceType(mainJobUpdateDTO.getServiceType());
        }
        if (mainJobUpdateDTO.getCost() != 0) {
            mainJob.setCost(mainJobUpdateDTO.getCost());
        }
        if (mainJobUpdateDTO.getReplacedPart() != null) {
            mainJob.setReplacedPart(mainJobUpdateDTO.getReplacedPart());
        }
        if (mainJobUpdateDTO.getReplacedImg() != null) {
            mainJob.setReplacedImg(mainJobUpdateDTO.getReplacedImg());
        }
        mainJob = mainJobRepository.save(mainJob);
        MainJobFullDTO mainJobDTO = modelMapper.map(mainJob, MainJobFullDTO.class);
        return mainJobDTO;
    }

    public void deleteMainJob(Long mainJobID) {
        mainJobRepository.deleteById(mainJobID);
    }
}