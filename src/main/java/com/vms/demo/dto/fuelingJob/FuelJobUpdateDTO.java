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
    private int fuelAmount;
    private int fuelCost;
    private String stationName;
    private int mileageBefore;
    private int mileageAfter;
    private int litersBefore;
    private int litersAfter;
}
