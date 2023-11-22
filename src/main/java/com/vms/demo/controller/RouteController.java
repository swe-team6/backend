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
import com.vms.demo.dto.route.RouteFullDTO;
import com.vms.demo.dto.route.RouteUpdateDTO;
import com.vms.demo.service.RouteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public List<RouteFullDTO> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("{routeID}")
    public RouteFullDTO retrieve(@PathVariable Long routeID) {
        return routeService.getRouteById(routeID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RouteCreateDTO create(@Valid @RequestBody RouteCreateDTO routeCreateDTO) {
        return routeService.createRoute(routeCreateDTO);
    }

    @PutMapping("{routeID}")
    public RouteFullDTO update(@PathVariable Long routeID, @RequestBody RouteUpdateDTO routeUpdateDTO) {
        return routeService.updateRoute(routeID, routeUpdateDTO);
    }

    @PostMapping("{routeID}/accept")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void accept(@PathVariable Long routeID) {
        routeService.acceptRouteById(routeID);
    }

    @PostMapping("{routeID}/start")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void start(@PathVariable Long routeID) {
        routeService.startRouteById(routeID);
    }

    @PostMapping("{routeID}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void complete(@PathVariable Long routeID) {
        routeService.completeRouteById(routeID);
    }

    @PostMapping("{routeID}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long routeID) {
        routeService.cancelRouteById(routeID);
    }
}
