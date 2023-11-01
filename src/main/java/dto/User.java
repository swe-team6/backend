package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private Long userID;
    //private Role role;
    private String phoneNumber;
    private String address;
    private String fullName;
    private String password;
    private Long govID;
    private String email;
}
