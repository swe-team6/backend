package com.vms.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Admins")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminID;
    private String email;
    private String password;
    private String name;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adminID == null) ? 0 : adminID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdminEntity other = (AdminEntity) obj;
        if (adminID == null) {
            if (other.adminID != null)
                return false;
        } else if (!adminID.equals(other.adminID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Admin [adminID=" + adminID + ", email=" + email + ", password=" + password + ", name=" + name + "]";
    }

}
