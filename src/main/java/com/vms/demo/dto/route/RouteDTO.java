package com.vms.demo.dto.route;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RouteDTO {
    private Long routeID;
    private String task;
    private String departurePlaceName;
    private String departureX;
    private String departureY;
    private String destinationPlaceName;
    private String destinationX;
    private String destinationY;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCompleted;
    /**
     * the status of the route. One of the following: Assigned, accepted, started,
     * completed, canceled
     */
    private RouteStatus status;
    private float distance;
}