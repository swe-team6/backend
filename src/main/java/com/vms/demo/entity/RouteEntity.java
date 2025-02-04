package com.vms.demo.entity;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.types.RouteStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCompleted;
    /**
     * the status of the route. One of the following: Assigned, accepted, started,
     * completed, canceled
     */
    private RouteStatus status;
    @Column(columnDefinition = "float default 0")
    private float distance;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private DriverEntity driver;

    @Override
    public String toString() {
        return "RouteEntity [routeID=" + routeID + ", task=" + task + ", departurePlaceName=" + departurePlaceName
                + ", departureX=" + departureX + ", departureY=" + departureY + ", destinationPlaceName="
                + destinationPlaceName + ", destinationX=" + destinationX + ", destinationY=" + destinationY
                + ", dateCreated=" + dateCreated + ", status=" + status + ", distance=" + distance + ", driver="
                + (driver != null ? driver.getUserID() : null) + "]";
    }

}
