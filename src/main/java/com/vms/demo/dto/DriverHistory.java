package com.vms.demo.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DriverHistory {
    private Long driverHistoryID;
    // carID
    // driverID
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime assignedDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime removedDate;
    /**
     * number of liters per kilometer the car uses on average for this driver
     */
    private int fuelConsumption;
    /**
     * total cost of all maintenance jobs while this driver was assigned to the car
     */
    private int maintenanceCost;
}
