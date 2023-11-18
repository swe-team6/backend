package com.vms.demo.entity;

import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverEntity {
    @Id
    @Column(name = "user_id")
    private Long userID;
    private String drivingLicense;
    /**
     * in kilometers
     */
    private int totalDistance;
    /**
     * total time spent on the task.
     */
    private Duration totalTime;
    private int JobsDone;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
