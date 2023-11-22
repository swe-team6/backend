package com.vms.demo.dto.maintenanceJob;

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
public class MainJobCreateDTO {
    private Long carID;
    private Long maintainerID;
    private String description;
    /**
     * a short description of the service type
     */
    private String serviceType;
    private int cost;
    /**
     * name of the replaced or concerned part
     */
    private String replacedPart;
    // private Image img;
}
