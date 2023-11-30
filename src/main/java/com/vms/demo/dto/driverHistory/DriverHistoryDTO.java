package com.vms.demo.dto.driverHistory;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DriverHistoryDTO {
    private Long driverHistoryID;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime assignedDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime removedDate;
    private float fuelConsumption;
    private int maintenanceCost;

    private Long carID;
    private Long driverID;
}