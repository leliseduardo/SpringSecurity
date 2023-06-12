package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.enums.RoleName;
import com.api.parkingcontrol.models.RoleModel;
import com.api.parkingcontrol.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    RoleModel getByRoleName(RoleName roleName);
}
