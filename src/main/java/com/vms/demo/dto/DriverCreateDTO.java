package com.vms.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DriverCreateDTO {
    // private Long userID;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long govID;
    private String drivingLicense;
    private String pictureUrl;
}
