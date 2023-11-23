package com.vms.demo.dto.fueler;

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
public class FuelerCreateDTO {
    private String phoneNumber;
    private String address;
    private String lastName;
    private String middleName;
    private String firstName;
    private String password;
    private Long govID;
    private String email;
    private String pictureUrl;
}
