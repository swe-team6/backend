package dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MaintenanceJob {
    private Long maintenanceJobID;
    // carID
    // maintainerID
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
    // private Image img;
}
