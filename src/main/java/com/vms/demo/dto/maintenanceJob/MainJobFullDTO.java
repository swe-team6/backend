package com.vms.demo.dto.maintenanceJob;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.types.CarStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MainJobFullDTO {
    private Long maintenanceJobID;
    private String description;
    /**
     * a short description of the service type
     */
    private String serviceType;
    private int cost;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateTime;
    /**
     * name of the replaced or concerned part
     */
    private String replacedPart;
    // private Image img;
    private MainJobCarDTO car;
    private MainJobUserDTO maintainer;
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class MainJobCarDTO {
    private Long carID;
    private String licensePlate;
    private String model;
    private int year;
    private int capacity;
    private String type;
    private String pictureUrl;
    private int mileage;
    private CarStatus status;
    private int mileageInterval;
    private Long timeInterval;
    private String maintenanceJson;
    private String usageDescription;
}

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class MainJobUserDTO {
    private String phoneNumber;
    private String address;
    private String lastName;
    private String middleName;
    private String firstName;
    private String password;
    private Long govID;
    private String email;
}