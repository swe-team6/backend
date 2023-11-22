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
import com.vms.demo.dto.route.RouteFullDTO;
import com.vms.demo.dto.route.RouteUpdateDTO;
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

    public List<RouteFullDTO> getAllRoutes() {
        List<RouteEntity> routes = routeRepository.findAll();
        return modelMapper.map(routes, new TypeToken<List<RouteFullDTO>>() {
        }.getType());
    }

    public RouteFullDTO getRouteById(Long routeID) {
        Optional<RouteEntity> routeOptional = routeRepository.findById(routeID);

        if (routeOptional.isPresent()) {
            RouteEntity route = routeOptional.get();
            RouteFullDTO routeDTO = modelMapper.map(route, RouteFullDTO.class);
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

    public RouteFullDTO updateRoute(Long routeID, RouteUpdateDTO routeUpdateDTO) {
        Optional<RouteEntity> routeOptional = routeRepository.findById(routeID);
        if (!routeOptional.isPresent()) {
            throw new EntityNotFoundException("Route not found with id: " + routeID);
        }
        RouteEntity route = routeOptional.get();
        if (routeUpdateDTO.getDeparturePoint() != null) {
            route.setDeparturePoint(routeUpdateDTO.getDeparturePoint());
        }
        if (routeUpdateDTO.getDestinationPoint() != null) {
            route.setDestinationPoint(routeUpdateDTO.getDestinationPoint());
        }
        if (routeUpdateDTO.getGMapsData() != null) {
            route.setGMapsData(routeUpdateDTO.getGMapsData());
        }
        if (routeUpdateDTO.getStatus() != null) {
            route.setStatus(routeUpdateDTO.getStatus());
        }
        if (routeUpdateDTO.getDriverID() != null) {
            Optional<DriverEntity> driverOptional = driverRepository.findById(routeUpdateDTO.getDriverID());
            if (!driverOptional.isPresent()) {
                throw new EntityNotFoundException("Driver not found with id: " + routeUpdateDTO.getDriverID());
            }
            DriverEntity driver = driverOptional.get();
            route.setDriver(driver);
        }
        route = routeRepository.save(route);
        RouteFullDTO routeDTO = modelMapper.map(route, RouteFullDTO.class);
        return routeDTO;
    }
}