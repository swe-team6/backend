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
public class DriverDTO {
    private Long userID;
    private String drivingLicense;
    private float totalDistance;
    private Long totalTime;
    private int jobsDone;
}