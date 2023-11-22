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
public class DriverUpdateDTO {
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long govID;
    private String pictureUrl;
    private String drivingLicense;
}