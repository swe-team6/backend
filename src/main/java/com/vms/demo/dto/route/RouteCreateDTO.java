package com.vms.demo.dto.route;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouteCreateDTO {
    private String task;
    private String departurePoint;
    private String destinationPoint;
    /**
     * the status of the route. One of the following: Assigned, accepted, started,
     * completed, canceled
     */
    private String gMapsData;
    @NotNull(message = "Driver is required")
    private Long driverID;
}