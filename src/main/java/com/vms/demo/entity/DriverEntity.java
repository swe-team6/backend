package com.vms.demo.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
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
    private long totalTime;
    private int jobsDone;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private CarEntity car;

    @OneToMany(mappedBy = "driver")
    private Set<RouteEntity> routes;

    @OneToMany(mappedBy = "driver")
    private Set<DriverHistoryEntity> histories;

    @OneToMany(mappedBy = "driver")
    private Set<FuelingJobEntity> fuelingJobs;

    @Override
    public String toString() {
        return "DriverEntity [userID=" + userID + ", drivingLicense=" + drivingLicense + ", totalDistance="
                + totalDistance + ", totalTime=" + totalTime + ", JobsDone=" + jobsDone + ", user=" + user + ", car="
                + car + ", routes=" + routes + ", histories=" + histories + ", fuelingJobs=" + fuelingJobs + "]";
    }
}
