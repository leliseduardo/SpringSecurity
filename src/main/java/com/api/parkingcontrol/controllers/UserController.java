package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.configs.security.UserDetailsServiceImpl;
import com.api.parkingcontrol.dtos.UserDto;
import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.repositories.RoleRepository;
import com.api.parkingcontrol.repositories.UserRepository;
import com.api.parkingcontrol.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserDetailsServiceImpl userService;

    private final RoleService roleService;

    public UserController(UserDetailsServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity get() {
        List<UserModel> users = userService.getUsers();

        List<UserDto> dtos = users.stream().map(user -> {
            UserDto dto = UserDto.create(user);
            dto.setRoleNames(user.getRoleNames());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){

    try {
        UserModel userModel = converter(userDto); // Converte DTO para UserModel e cria UserModel
        for (RoleName role : userDto.getRoleNames()) {
            RoleModel roleModel = roleService.getByRoleName(role);
            roleModel.getUsers().add(userModel);
            userModel.getRoles().add(roleModel);
        }
        userModel = userService.save(userModel);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
    catch (Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    public UserModel converter(UserDto dto) {
        ModelMapper modelMapper = new ModelMapper();
        dto.setRoleNames(dto.getRoleNames().stream().map(role -> RoleName.valueOf(String.valueOf(role))).collect(Collectors.toList()));
        UserModel user = new UserModel();
        user = modelMapper.map(dto, UserModel.class);
        return user;
    }
}
