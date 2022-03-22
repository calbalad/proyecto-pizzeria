package com.pizzaiolo.authorization.services;

import static com.pizzaiolo.authorization.utils.Constants.INVALID_TOKEN_MESSAGE;
import static com.pizzaiolo.authorization.utils.Constants.RESOURCE_NOT_FOUND_MESSAGE;

import com.pizzaiolo.authorization.repositories.UserAccountRepository;
import com.pizzaiolo.authorization.services.interfaces.UserAccountService;
import com.pizzaiolo.authorization.exceptions.ResourceNotFoundException;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.*;

import com.pizzaiolo.authorization.models.entities.UserAccount;
import com.pizzaiolo.authorization.models.entities.User;


@Service(value = "userAccountService")
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount save(User user, String token) {
        UserAccount newUserAccount = new UserAccount();
        Date dateNow = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dateNow);
        c.add(Calendar.DATE, 2);

        newUserAccount.setUser(user)
                .setToken(token)
                .setExpireAt(c.getTime().getTime());

        return userAccountRepository.save(newUserAccount);
    }

    @Override
    public List<UserAccount> findAll() {
        List<UserAccount> list = new ArrayList<>();
        userAccountRepository.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public void delete(String id) {
        userAccountRepository.deleteById(new ObjectId(id));
    }

    @Override
    public UserAccount findByToken(String token) throws ResourceNotFoundException {
        Optional<UserAccount> userAccountOptional = userAccountRepository.findByToken(token);

        if (userAccountOptional.isEmpty()) {
            throw new ResourceNotFoundException(INVALID_TOKEN_MESSAGE);
        }

        return userAccountOptional.get();
    }

    @Override
    public UserAccount findById(String id) throws ResourceNotFoundException {
        Optional<UserAccount> confirmAccountOptional = userAccountRepository.findById(new ObjectId(id));

        if (confirmAccountOptional.isEmpty()) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE);
        }

        return confirmAccountOptional.get();
    }
}
