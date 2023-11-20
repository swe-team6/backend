package com.vms.demo.entity;

import java.time.Duration;
import java.util.Set;

import com.vms.demo.types.CarStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carID;
    private int licensePlate;
    private String model;
    private int year;
    private int capacity;
    private int mileage;
    private CarStatus status;
    private int mileageInterval;
    private Duration timeInterval;
    private String maintenanceJson;

    @OneToOne(mappedBy = "car")
    private DriverEntity driver;

    @OneToOne(mappedBy = "car")
    private BiddingEntity bidding;

    @OneToMany(mappedBy = "car")
    private Set<DriverHistoryEntity> histories;

    @OneToMany(mappedBy = "car")
    private Set<MaintenanceJobEntity> maintenanceJobs;

    @OneToMany(mappedBy = "car")
    private Set<FuelingJobEntity> fuelingJobs;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carID == null) ? 0 : carID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CarEntity other = (CarEntity) obj;
        if (carID == null) {
            if (other.carID != null)
                return false;
        } else if (!carID.equals(other.carID))
            return false;
        return true;
    }
}
