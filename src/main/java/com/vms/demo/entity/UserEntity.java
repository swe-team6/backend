package com.vms.demo.entity;

import java.util.Set;

import com.vms.demo.types.RoleType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;
    private RoleType role;
    private String phoneNumber;
    private String address;
    private String lastName;
    private String middleName;
    private String firstName;
    private String password;
    private Long govID;
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private DriverEntity driver;

    @OneToOne(mappedBy = "user")
    private DriverEntity chat;

    @OneToMany(mappedBy = "maintainer")
    private Set<MaintenanceJobEntity> maintenanceJobs;

    @OneToMany(mappedBy = "fueler")
    private Set<FuelingJobEntity> fuelingJobs;
}
