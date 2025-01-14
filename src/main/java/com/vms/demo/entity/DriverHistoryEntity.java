package com.vms.demo.entity;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "DriversHistory")
public class DriverHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverHistoryID;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime assignedDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime removedDate;
    /**
     * total number of liters the car used for this driver
     */
    private float fuelConsumption;
    /**
     * total cost of all maintenance jobs while this driver was assigned to the car
     */
    private int maintenanceCost;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = true)
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true)
    private DriverEntity driver;
}
