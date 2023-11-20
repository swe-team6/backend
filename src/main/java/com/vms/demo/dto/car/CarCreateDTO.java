package com.vms.demo.dto.car;

import org.springframework.data.annotation.ReadOnlyProperty;

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
public class CarCreateDTO {
    @ReadOnlyProperty
    private Long carID;
    private int licensePlate;
    private String model;
    private int year;
    private int capacity;
    private String type;
    private String pictureUrl;
    private int mileage;
    private int mileageInterval;
    private Long timeInterval;
    private String usageDescription;
}
