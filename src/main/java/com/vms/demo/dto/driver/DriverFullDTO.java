package com.vms.demo.dto.driver;

import com.vms.demo.types.CarStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverFullDTO {
    private Long userID;
    private String drivingLicense;
    private int totalDistance;
    private Long totalTime;
    private int jobsDone;
    private DriverUserDTO user;
    private DriverCarDTO car;
}

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class DriverUserDTO {
    private String phoneNumber;
    private String address;
    private String lastName;
    private String middleName;
    private String firstName;
    private Long govID;
    private String email;
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class DriverCarDTO {
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