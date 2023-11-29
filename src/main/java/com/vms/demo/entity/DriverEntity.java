package com.vms.demo.entity;

import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
@Table(name = "Drivers")
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

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @PrimaryKeyJoinColumn
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private CarEntity car;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private Set<RouteEntity> routes;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private Set<DriverHistoryEntity> histories;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private Set<FuelJobEntity> fuelingJobs;

    @Override
    public String toString() {
        return "DriverEntity [userID=" + userID + ", drivingLicense=" + drivingLicense + ", totalDistance="
                + totalDistance + ", totalTime=" + totalTime + ", JobsDone=" + jobsDone + ", car="
                + car + ", routes=" + routes + ", histories=" + histories + ", fuelingJobs=" + fuelingJobs + "]";
    }

    @PreRemove
    public void preRemove() {
        this.car = null;
    }
}
