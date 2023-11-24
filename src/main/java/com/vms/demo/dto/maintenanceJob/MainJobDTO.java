package com.vms.demo.dto.maintenanceJob;

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
public class MainJobDTO {
    private Long maintenanceJobID;
    // carID
    // maintainerID
    private String description;
    /**
     * a short description of the service type
     */
    private String serviceType;
    private int cost;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateTime;
    /**
     * name of the replaced or concerned part
     */
    private String replacedPart;
    private String replacedImg;
}
