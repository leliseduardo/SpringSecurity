package com.api.parkingcontrol.dtos;

import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.models.UserModel;
import net.bytebuddy.build.RepeatedAnnotationPlugin;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

public class UserDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private List<RoleName> roleNames;

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

    public List<RoleName> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<RoleName> roleNames) {
        this.roleNames = roleNames;
    }

    public static UserDto create(UserModel userModel) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userModel, UserDto.class);
    }
}
