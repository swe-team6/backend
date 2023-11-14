package dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class FuelingJob {
    private Long fuelingJobID;
    // carID
    // driverID
    // fuelerID
    private int fuelAmount;
    private int fuelCost;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private ZonedDateTime dateTime;
    private String stationName;
    private int mileageBefore;
    private int mileageAfter;
    private int litersBefore;
    private int litersAfter;
}
