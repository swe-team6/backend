package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Car {
    private Long carID;
    private int licensePlate;
    private String model;
    private int year;
    private int capacity;
    private int mileage;


}
