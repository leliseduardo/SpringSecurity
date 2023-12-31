package com.api.parkingcontrol.services;

import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    final
    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleModel getByRoleName(RoleName roleName) { return roleRepository.getByRoleName(roleName); }

    @Transactional
    public RoleModel save(RoleModel roleModel) {
        return roleRepository.save(roleModel);
    }

    public Page<RoleModel> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public Optional<RoleModel> findById(UUID roleId) {
        return roleRepository.findById(roleId);
    }

    @Transactional
    public void delete(RoleModel roleModel) {
        roleRepository.delete(roleModel);
    }
}
