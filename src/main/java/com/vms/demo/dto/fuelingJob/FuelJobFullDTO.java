package com.vms.demo.dto.fuelingJob;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.dto.car.CarDTO;
import com.vms.demo.dto.driver.DriverDTO;
import com.vms.demo.dto.fueler.FuelerDTO;

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
public class FuelJobFullDTO {
    private Long fuelingJobID;
    private int fuelAmount;
    private int fuelCost;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateTime;
    private String stationName;
    private int mileageBefore;
    private int mileageAfter;
    private int litersBefore;
    private int litersAfter;
    private CarDTO car;
    private FuelerDTO fueler;
    private DriverDTO driver;
}