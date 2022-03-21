package com.pizzaiolo.authorization.repositories;

import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pizzaiolo.authorization.models.entities.User;

@Repository(value = "com.pizzaiolo.authorization.repositories.UserRepository")
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByEmail(String email);
}
