package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Admin {
    private Long adminID;
    private String email;
    private String password;
    private String name;

}

