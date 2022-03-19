package com.pizzaiolo.authorization.services.interfaces;


import java.util.List;

import com.pizzaiolo.authorization.models.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    void delete(String id);

    User findByEmail(String email);

    User findById(String id);

    void update(User user);

}
