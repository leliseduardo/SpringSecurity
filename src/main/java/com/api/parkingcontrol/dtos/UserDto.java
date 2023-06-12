package com.api.parkingcontrol.dtos;

import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import net.bytebuddy.build.RepeatedAnnotationPlugin;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
