package com.vms.demo.service;

import com.vms.demo.dto.Admin;
import com.vms.demo.dto.UserDTO;
import com.vms.demo.entity.AdminEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.AdminRepository;
import com.vms.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    static{
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }
    public Admin getAdminByEmail(String adminEmail) {
        Optional<AdminEntity> maybeAdmin = adminRepository.findByEmail(adminEmail);
        if (maybeAdmin.isEmpty()) {
            throw new EntityNotFoundException("Admin not found with email: " + adminEmail);
        }
        AdminEntity admin = maybeAdmin.get();
        return modelMapper.map(admin, Admin.class);
    }
    public UserDTO getUserByEmail(String userEmail) {
        Optional<UserEntity> maybeUser = userRepository.findByEmail(userEmail);
        if (maybeUser.isEmpty()) {
            throw new EntityNotFoundException("User not found with id: " + userEmail);
        }
        UserEntity user = maybeUser.get();
        return modelMapper.map(user, UserDTO.class);
    }
}
