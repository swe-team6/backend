package com.vms.demo.entity;

import java.util.Set;

import com.vms.demo.types.CarStatus;

import jakarta.persistence.CascadeType;
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
    private String licensePlate;
    private String model;
    private int year;
    private int capacity;
    private String type;
    private String pictureUrl;
    private int mileage;
    private CarStatus status;
    private int mileageInterval;
    private long timeInterval;
    private String usageDescription;
    private String maintenanceJson;

    @OneToOne(mappedBy = "car")
    private DriverEntity driver;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private BiddingEntity bidding;

    @OneToMany(mappedBy = "car")
    private Set<DriverHistoryEntity> histories;

    @OneToMany(mappedBy = "car")
    private Set<MainJobEntity> maintenanceJobs;

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

    @Override
    public String toString() {
        return "CarEntity [carID=" + carID + ", licensePlate=" + licensePlate + ", model=" + model + ", year=" + year
                + ", capacity=" + capacity + ", type=" + type + ", pictureUrl=" + pictureUrl + ", mileage=" + mileage
                + ", status=" + status + ", mileageInterval=" + mileageInterval + ", timeInterval=" + timeInterval
                + ", usageDescription=" + usageDescription + ", maintenanceJson=" + maintenanceJson + ", driver="
                + driver.getUserID() + ", bidding=" + bidding + ", histories=" + histories + ", maintenanceJobs="
                + maintenanceJobs
                + ", fuelingJobs=" + fuelingJobs + "]";
    }
}
