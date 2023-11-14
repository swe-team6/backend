package dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Route {
    private Long routeID;
    // driverID
    private String task;
    // private <Type> departurePoint;
    // private <Type> destinationPoint;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCreated;
    /**
     * the status of the route. One of the following: Assigned, accepted, started,
     * completed, canceled
     */
    // private RouteStatus status;
    private String gMapsData;
}
