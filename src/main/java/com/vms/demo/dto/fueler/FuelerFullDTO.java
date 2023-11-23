package com.vms.demo.dto.fueler;

import java.util.List;

import com.vms.demo.dto.fuelingJob.FuelJobDTO;
import com.vms.demo.types.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuelerFullDTO {
    private Long userID;
    private RoleType role;
    private String phoneNumber;
    private String address;
    private String lastName;
    private String middleName;
    private String firstName;
    private Long govID;
    private String email;
    private String pictureUrl;
    private List<FuelJobDTO> fuelingJobs;
}
