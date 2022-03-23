package com.pizzaiolo.authorization.services.interfaces;

import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;
import com.pizzaiolo.authorization.models.entities.UserAccount;
import com.pizzaiolo.authorization.models.entities.User;

import java.util.List;

public interface UserAccountService {
    UserAccount save(User user, String token);

    List<UserAccount> findAll();

    void delete(String id);

    UserAccount findByToken(String token) throws ResourceNotFoundException;

    UserAccount findById(String id) throws ResourceNotFoundException;
}
