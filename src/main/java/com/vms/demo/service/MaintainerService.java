package com.vms.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vms.demo.dto.maintainer.MaintainerCreateDTO;
import com.vms.demo.dto.maintainer.MaintainerDTO;
import com.vms.demo.dto.maintainer.MaintainerFullDTO;
import com.vms.demo.dto.maintainer.MaintainerUpdateDTO;
import com.vms.demo.entity.UserEntity;
import com.vms.demo.repository.MaintainerRepository;
import com.vms.demo.types.RoleType;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MaintainerService {

    @Autowired
    private MaintainerRepository maintainerRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    public static void main(String[] args) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<MaintainerFullDTO> getAllMaintainers() {
        List<UserEntity> maintainers = maintainerRepository.findByRoleOrderByUserIDAsc(RoleType.MAINTAINER);
        return modelMapper.map(maintainers, new TypeToken<List<MaintainerFullDTO>>() {
        }.getType());
    }

    public MaintainerFullDTO getMaintainerById(Long maintainerID) {
        List<UserEntity> maintainers = maintainerRepository.findByUserIDAndRole(maintainerID, RoleType.MAINTAINER);
        if (maintainers.isEmpty()) {
            throw new EntityNotFoundException("Maintainer not found with id: " + maintainerID);
        }
        UserEntity maintainer = maintainers.get(0);
        MaintainerFullDTO maintainerDTO = modelMapper.map(maintainer, MaintainerFullDTO.class);
        return maintainerDTO;
    }

    public MaintainerCreateDTO createMaintainer(MaintainerCreateDTO maintainerCreateDTO) {
        UserEntity maintainer = modelMapper.map(maintainerCreateDTO, UserEntity.class);
        maintainer.setRole(RoleType.MAINTAINER);
        maintainer = maintainerRepository.save(maintainer);
        MaintainerCreateDTO dto = modelMapper.map(maintainer, MaintainerCreateDTO.class);
        return dto;
    }

    public MaintainerDTO updateMaintainer(Long maintainerID, MaintainerUpdateDTO maintainerUpdateDTO) {
        List<UserEntity> maintainers = maintainerRepository.findByUserIDAndRole(maintainerID, RoleType.MAINTAINER);
        if (maintainers.isEmpty()) {
            throw new EntityNotFoundException("Maintainer not found with id: " + maintainerID);
        }
        UserEntity maintainer = maintainers.get(0);
        if (maintainerUpdateDTO.getPhoneNumber() != null) {
            maintainer.setPhoneNumber(maintainerUpdateDTO.getPhoneNumber());
        }
        if (maintainerUpdateDTO.getEmail() != null) {
            maintainer.setEmail(maintainerUpdateDTO.getEmail());
        }
        if (maintainerUpdateDTO.getAddress() != null) {
            maintainer.setAddress(maintainerUpdateDTO.getAddress());
        }
        if (maintainerUpdateDTO.getFirstName() != null) {
            maintainer.setFirstName(maintainerUpdateDTO.getFirstName());
        }
        if (maintainerUpdateDTO.getMiddleName() != null) {
            maintainer.setMiddleName(maintainerUpdateDTO.getMiddleName());
        }
        if (maintainerUpdateDTO.getLastName() != null) {
            maintainer.setLastName(maintainerUpdateDTO.getLastName());
        }
        if (maintainerUpdateDTO.getGovID() != null) {
            maintainer.setGovID(maintainerUpdateDTO.getGovID());
        }
        if (maintainerUpdateDTO.getPictureUrl() != null) {
            maintainer.setPictureUrl(maintainerUpdateDTO.getPictureUrl());
        }
        // TODO: Hash password
        if (maintainerUpdateDTO.getPassword() != null) {
            maintainer.setPassword(maintainerUpdateDTO.getPassword());
        }
        maintainer = maintainerRepository.save(maintainer);
        MaintainerDTO maintainerDTO = modelMapper.map(maintainer, MaintainerDTO.class);
        return maintainerDTO;
    }

    public void deleteMaintainer(Long maintainerID) {
        // TODO: fix
        maintainerRepository.deleteByUserIDAndRole(maintainerID, RoleType.MAINTAINER);
    }
}