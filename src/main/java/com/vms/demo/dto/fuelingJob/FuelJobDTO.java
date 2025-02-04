package com.vms.demo.dto.fuelingJob;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class FuelJobDTO {
    private Long fuelingJobID;
    private Long carID;
    private Long driverID;
    private float fuelAmount;
    private int fuelCost;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateTime;
    private String stationName;
    private int mileageBefore;
    private int mileageAfter;
    private float litersBefore;
    private float litersAfter;
}
