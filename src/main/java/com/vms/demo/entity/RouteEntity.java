package com.vms.demo.entity;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.types.RouteStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeID;
    private String task;
    private String departurePoint;
    private String destinationPoint;
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
        return "RouteEntity [routeID=" + routeID + ", task=" + task + ", departurePoint=" + departurePoint
                + ", destinationPoint=" + destinationPoint + ", dateCreated=" + dateCreated + ", status=" + status
                + ", gMapsData=" + gMapsData + ", driver=" + driver + "]";
    }
}
