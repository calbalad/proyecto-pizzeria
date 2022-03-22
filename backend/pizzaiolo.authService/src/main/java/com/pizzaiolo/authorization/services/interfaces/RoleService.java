package com.pizzaiolo.authorization.services.interfaces;


import com.pizzaiolo.authorization.models.entities.Role;

import java.util.List;

public interface RoleService {


    List<Role> findAll();

    void delete(String id);

    Role update(Role role);
}
