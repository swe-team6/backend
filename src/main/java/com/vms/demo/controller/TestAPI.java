package com.vms.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.Admin;

@RestController
@RequestMapping("/")
public class TestAPI {

    @GetMapping("{adminID}")
    public Admin retrieve(Long adminID) {
        return Admin.builder().adminID(adminID).email("admin@team6.edu").password("password").name("Admin").build();
    }
}
