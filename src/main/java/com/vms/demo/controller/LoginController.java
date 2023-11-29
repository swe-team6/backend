package com.vms.demo.controller;

import com.vms.demo.dto.Admin;
import com.vms.demo.dto.LoginRequest;
import com.vms.demo.dto.UserDTO;
import com.vms.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        try {
            Admin admin = loginService.getAdminByEmail(username);
            if (password.equals(admin.getPassword())) {
                return ResponseEntity.ok(admin);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password for admin");
            }
        } catch (EntityNotFoundException adminNotFoundException) {
            try {
                UserDTO user = loginService.getUserByEmail(username);
                if (password.equals(user.getPassword())) {
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password for user");
                }
            } catch (EntityNotFoundException userNotFoundException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
    }
}
