package com.vms.demo.entity;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.types.RouteStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Routes")
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeID;
    private String task;
    private String departurePlaceName;
    private String departureX;
    private String departureY;
    private String destinationPlaceName;
    private String destinationX;
    private String destinationY;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCreated;
    /**
     * the status of the route. One of the following: Assigned, accepted, started,
     * completed, canceled
     */
    private RouteStatus status;
    private String gMapsData;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private DriverEntity driver;

    @Override
    public String toString() {
        return "RouteEntity [routeID=" + routeID + ", task=" + task + ", departurePlaceName=" + departurePlaceName
                + ", departureX=" + departureX + ", departureY=" + departureY + ", destinationPlaceName="
                + destinationPlaceName + ", destinationX=" + destinationX + ", destinationY=" + destinationY
                + ", dateCreated=" + dateCreated + ", status=" + status + ", gMapsData=" + gMapsData + ", driver="
                + (driver != null ? driver.getUserID() : null) + "]";
    }

}
