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
public class FuelJobCreateDTO {
    private Long carID;
    private Long fuelerID;
    private Long driverID;
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
}
