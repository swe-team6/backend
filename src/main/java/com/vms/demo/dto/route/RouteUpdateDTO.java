package com.vms.demo.dto.route;

import com.vms.demo.types.RouteStatus;

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
public class RouteUpdateDTO {
    private String task;
    private String departurePlaceName;
    private String departureX;
    private String departureY;
    private String destinationPlaceName;
    private String destinationX;
    private String destinationY;
    /**
     * the status of the route. One of the following: Assigned, accepted, started,
     * completed, canceled
     */
    private RouteStatus status;
    private float distance;
    private Long driverID;
}