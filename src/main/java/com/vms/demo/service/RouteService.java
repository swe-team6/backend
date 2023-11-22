package com.vms.demo.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.route.RouteCreateDTO;
import com.vms.demo.dto.route.RouteDTO;
import com.vms.demo.entity.DriverEntity;
import com.vms.demo.entity.RouteEntity;
import com.vms.demo.repository.DriverRepository;
import com.vms.demo.repository.RouteRepository;
import com.vms.demo.types.RouteStatus;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private DriverRepository driverRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<RouteDTO> getAllRoutes() {
        List<RouteEntity> routes = routeRepository.findAll();
        return modelMapper.map(routes, new TypeToken<List<RouteDTO>>() {
        }.getType());
    }

    public RouteDTO getRouteById(Long routeID) {
        Optional<RouteEntity> routeOptional = routeRepository.findById(routeID);

        if (routeOptional.isPresent()) {
            RouteEntity route = routeOptional.get();
            // UserEntity user = route.getUser();
            RouteDTO routeDTO = modelMapper.map(route, RouteDTO.class);
            // Map entities to DTO
            return routeDTO;
        } else {
            throw new EntityNotFoundException("Route not found with id: " + routeID);
        }
    }

    public RouteCreateDTO createRoute(RouteCreateDTO routeCreateDTO) {
        RouteEntity e = modelMapper.map(routeCreateDTO, RouteEntity.class);
        System.out.println(routeCreateDTO.getDriverID());
        e.setStatus(RouteStatus.ASSIGNED);
        e.setDateCreated(ZonedDateTime.now());
        Optional<DriverEntity> driverOptional = driverRepository.findById(routeCreateDTO.getDriverID());
        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Invalid driver id (not found): " + routeCreateDTO.getDriverID());
        }
        DriverEntity driver = driverOptional.get();
        e.setDriver(driver);
        e = routeRepository.save(e);
        RouteCreateDTO dto = modelMapper.map(e, RouteCreateDTO.class);
        return dto;
    }

    public RouteDTO assignDriver(Long routeID, Long driverID) {
        Optional<DriverEntity> driverOptional = driverRepository.findById(routeID);
        RouteEntity route = routeRepository.findByRouteID(routeID);
        if (!driverOptional.isPresent()) {
            throw new EntityNotFoundException("Route not found with id: " + routeID);
        }
        DriverEntity driver = driverOptional.get();
        // if (route.getDriver() != null) {
        // throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
        // "Requested route is already assigned to a route.");
        // }

        route.setDriver(driver);
        route = routeRepository.save(route);
        RouteDTO routeDTO = modelMapper.map(route, RouteDTO.class);
        return routeDTO;
    }
}