package com.vms.demo.entity;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vms.demo.types.BiddingStatus;
import com.vms.demo.types.CarStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PreRemove;
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
@Table(name = "Biddings")
public class BiddingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biddingID;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateCreated;
    // imgs
    private BiddingStatus status;
    private String info;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "car_id")
    private CarEntity car;

    @PreRemove
    public void preRemove() {
        if (this.getCar() != null) {
            if (this.getStatus() != BiddingStatus.CLOSED) {
                this.car.setStatus(CarStatus.INACTIVE);
            }
            this.car.setBidding(null);
        }
    }

    @Override
    public String toString() {
        return "BiddingEntity [biddingID=" + biddingID + ", dateCreated=" + dateCreated + ", status=" + status
                + ", info=" + info + ", car=" + ((car == null) ? null : car.getCarID()) + "]";
    }
}
