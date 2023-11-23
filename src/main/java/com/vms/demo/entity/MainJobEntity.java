package com.vms.demo.entity;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class MainJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maintenanceJobID;
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
    // private Image replacedImg;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = true)
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "maintainer_id", nullable = true)
    private UserEntity maintainer;
}
