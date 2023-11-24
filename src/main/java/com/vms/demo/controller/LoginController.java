package com.vms.demo.controller;

import com.vms.demo.dto.LoginRequest;
import com.vms.demo.entity.AdminEntity;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.AdminRepository;
import com.vms.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<AdminEntity> maybeAdmin = adminRepository.findByEmail(username);
        if (maybeAdmin.isPresent()) {
            AdminEntity admin = maybeAdmin.get();
            if (password.equals(admin.getPassword())) {
                return ResponseEntity.ok(admin);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password for admin");
            }
        }
        Optional<UserEntity> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (password.equals(user.getPassword())) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password for user");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
