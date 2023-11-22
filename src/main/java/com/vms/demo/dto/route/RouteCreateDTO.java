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
public class RouteCreateDTO {
    private Long routeID;
    private String task;
    private String departurePoint;
    private String destinationPoint;
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
    // "yyyy-MM-dd'T'HH:mm:ssZ")
    // private ZonedDateTime dateCreated;
    /**
     * the status of the route. One of the following: Assigned, accepted, started,
     * completed, canceled
     */
    private RouteStatus status;
    private String gMapsData;

    private Long driverID;
}