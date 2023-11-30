package com.vms.demo.dto.driverHistory;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.dto.car.CarDTO;
import com.vms.demo.dto.driver.DriverDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DriverHistoryFullDTO {
    private Long driverHistoryID;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime assignedDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime removedDate;
    private float fuelConsumption;
    private int maintenanceCost;

    private CarDTO car;
    // private Long driverUserID;
    private DriverDTO driver;
}
