package com.vms.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.fueler.FuelerCreateDTO;
import com.vms.demo.dto.fueler.FuelerDTO;
import com.vms.demo.dto.fueler.FuelerFullDTO;
import com.vms.demo.dto.fueler.FuelerUpdateDTO;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.FuelerRepository;
import com.vms.demo.types.RoleType;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FuelerService {

    @Autowired
    private FuelerRepository fuelerRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<FuelerFullDTO> getAllFuelers() {
        List<UserEntity> fuelers = fuelerRepository.findByRoleOrderByUserIDAsc(RoleType.FUELER);
        return modelMapper.map(fuelers, new TypeToken<List<FuelerFullDTO>>() {
        }.getType());
    }

    public FuelerFullDTO getFuelerById(Long fuelerID) {
        List<UserEntity> fuelers = fuelerRepository.findByUserIDAndRole(fuelerID, RoleType.FUELER);
        if (fuelers.isEmpty()) {
            throw new EntityNotFoundException("Fueler not found with id: " + fuelerID);
        }
        UserEntity fueler = fuelers.get(0);
        return modelMapper.map(fueler, FuelerFullDTO.class);
    }

    public FuelerFullDTO createFueler(FuelerCreateDTO fuelerCreateDTO) {
        UserEntity fueler = modelMapper.map(fuelerCreateDTO, UserEntity.class);
        fueler.setRole(RoleType.FUELER);
        fueler = fuelerRepository.save(fueler);
        return modelMapper.map(fueler, FuelerFullDTO.class);
    }

    public FuelerDTO updateFueler(Long fuelerID, FuelerUpdateDTO fuelerUpdateDTO) {
        List<UserEntity> fuelers = fuelerRepository.findByUserIDAndRole(fuelerID, RoleType.FUELER);
        if (fuelers.isEmpty()) {
            throw new EntityNotFoundException("Fueler not found with id: " + fuelerID);
        }
        UserEntity fueler = fuelers.get(0);
        if (fuelerUpdateDTO.getPhoneNumber() != null) {
            fueler.setPhoneNumber(fuelerUpdateDTO.getPhoneNumber());
        }
        if (fuelerUpdateDTO.getEmail() != null) {
            fueler.setEmail(fuelerUpdateDTO.getEmail());
        }
        if (fuelerUpdateDTO.getAddress() != null) {
            fueler.setAddress(fuelerUpdateDTO.getAddress());
        }
        if (fuelerUpdateDTO.getFirstName() != null) {
            fueler.setFirstName(fuelerUpdateDTO.getFirstName());
        }
        if (fuelerUpdateDTO.getMiddleName() != null) {
            fueler.setMiddleName(fuelerUpdateDTO.getMiddleName());
        }
        if (fuelerUpdateDTO.getLastName() != null) {
            fueler.setLastName(fuelerUpdateDTO.getLastName());
        }
        if (fuelerUpdateDTO.getGovID() != null) {
            fueler.setGovID(fuelerUpdateDTO.getGovID());
        }
        if (fuelerUpdateDTO.getPictureUrl() != null) {
            fueler.setPictureUrl(fuelerUpdateDTO.getPictureUrl());
        }
        // TODO: Hash password
        if (fuelerUpdateDTO.getPassword() != null) {
            fueler.setPassword(fuelerUpdateDTO.getPassword());
        }
        fueler = fuelerRepository.save(fueler);
        return modelMapper.map(fueler, FuelerDTO.class);
    }

    public void deleteFueler(Long fuelerID) {
        fuelerRepository.deleteByUserIDAndRole(fuelerID, RoleType.FUELER);
    }
}