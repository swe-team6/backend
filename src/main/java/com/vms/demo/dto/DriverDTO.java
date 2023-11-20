package com.vms.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DriverDTO {
    private Long userID;
    private String phoneNumber;
    private String address;
    private String lastName;
    private String middleName;
    private String firstName;
    private String password;
    private Long govID;
    private String email;
    private String drivingLicense;
    private int totalDistance;
    private Long totalTime;
    private int JobsDone;
}
