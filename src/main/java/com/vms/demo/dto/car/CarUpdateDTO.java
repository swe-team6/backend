package com.vms.demo.dto.car;

import com.vms.demo.types.CarStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarUpdateDTO {
    private String licensePlate;
    private String model;
    private int year;
    private int capacity;
    private String type;
    private String pictureUrl;
    private int mileage;
    private CarStatus status;
    private int mileageInterval;
    private Long timeInterval;
    private String maintenanceJson;
    private String usageDescription;
}