package com.vms.demo.dto.maintainer;

import java.util.List;

import com.vms.demo.dto.maintenanceJob.MainJobDTO;
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
public class MaintainerFullDTO {
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
    private List<MainJobDTO> maintenanceJobs;
}
