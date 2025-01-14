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
@Table(name = "Fuel_Jobs")
public class FuelJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelingJobID;
    private float fuelAmount;
    private int fuelCost;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateTime;
    private String stationName;
    private int mileageBefore;
    private int mileageAfter;
    private float litersBefore;
    private float litersAfter;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = true)
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true)
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "fueler_id", nullable = true)
    private UserEntity fueler;
}
