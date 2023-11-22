package com.vms.demo.dto.driver;

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
    private String password;
    private Long govID;
    private String email;
}