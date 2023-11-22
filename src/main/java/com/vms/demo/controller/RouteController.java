package com.vms.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vms.demo.dto.route.RouteCreateDTO;
import com.vms.demo.dto.route.RouteDTO;
import com.vms.demo.dto.route.RouteUpdateDTO;
import com.vms.demo.service.RouteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public List<RouteDTO> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("{routeID}")
    public RouteDTO retrieve(@PathVariable Long routeID) {
        return routeService.getRouteById(routeID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RouteCreateDTO create(@Valid @RequestBody RouteCreateDTO routeCreateDTO) {
        return routeService.createRoute(routeCreateDTO);
    }

    @PutMapping("{routeID}")
    @ResponseStatus(HttpStatus.OK)
    public RouteDTO update(@PathVariable Long routeID, @RequestBody RouteUpdateDTO routeUpdateDTO) {
        return routeService.updateRoute(routeID, routeUpdateDTO);
    }
}
