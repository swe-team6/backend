package com.vms.demo.dto.maintainer;

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
public class MaintainerDTO {
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
}
