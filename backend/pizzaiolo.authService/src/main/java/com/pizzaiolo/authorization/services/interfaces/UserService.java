package com.pizzaiolo.authorization.services.interfaces;

import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;
import java.util.List;

import com.pizzaiolo.authorization.models.dtos.UpdatePasswordDto;
import com.pizzaiolo.authorization.models.dtos.UpdateRolDto;
import com.pizzaiolo.authorization.models.dtos.UpdateRoleDto;
import com.pizzaiolo.authorization.models.dtos.UpdateUserDto;
import com.pizzaiolo.authorization.models.dtos.CreateUserDto;
import com.pizzaiolo.authorization.models.entities.Role;
import com.pizzaiolo.authorization.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(CreateUserDto createUserDto);

    List<User> findAll();

    void delete(String id);

    User findByEmail(String email) throws ResourceNotFoundException;

    User findById(String id) throws ResourceNotFoundException;

    User update(String id, UpdateUserDto updateUserDto) throws ResourceNotFoundException;

    void update(User user);

    User updatePassword(String id, UpdatePasswordDto updatePasswordDto)
        throws ResourceNotFoundException;

    void updatePassword(String id, String newPassword) throws ResourceNotFoundException;
    
    User updateRol(String id, UpdateRolDto updateRolDto)
            throws ResourceNotFoundException;

    void confirm(String id) throws ResourceNotFoundException;
    
    Boolean existsByEmail(String email);
    
}
