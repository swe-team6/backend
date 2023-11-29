package com.vms.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.UserDTO;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "userID"));
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());
    }
}