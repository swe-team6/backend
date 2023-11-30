package com.vms.demo.dto.fuelingJob;

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
public class FuelJobUpdateDTO {
    private float fuelAmount;
    private int fuelCost;
    private String stationName;
    private int mileageBefore;
    private int mileageAfter;
    private float litersBefore;
    private float litersAfter;
}
