package com.vms.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;
    @Column
    private RoleType role;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String lastName;
    @Column
    private String middleName;
    @Column
    private String firstName;
    @Column
    private String password;
    @Column
    private Long govID;
    @Column(unique = true)
    private String email;
    @Column
    private String pictureUrl;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @PrimaryKeyJoinColumn
    private DriverEntity driver;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ChatEntity chat;

    @OneToMany(mappedBy = "maintainer", cascade = CascadeType.ALL)
    private Set<MainJobEntity> maintenanceJobs;

    @OneToMany(mappedBy = "fueler", cascade = CascadeType.ALL)
    private Set<FuelJobEntity> fuelingJobs;

    @Override
    public String toString() {
        return "UserEntity [userID=" + userID + ", role=" + role + ", phoneNumber=" + phoneNumber + ", address="
                + address + ", lastName=" + lastName + ", middleName=" + middleName + ", firstName=" + firstName
                + ", password=" + password + ", govID=" + govID + ", email=" + email + ", pictureUrl=" + pictureUrl
                + ", driver=" + driver + ", chat=" + chat + ", maintenanceJobs=" + maintenanceJobs + ", fuelingJobs="
                + fuelingJobs + "]";
    }

}
