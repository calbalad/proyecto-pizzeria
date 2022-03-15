package com.pizzaiolo.spring.jwt.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pizzaiolo.spring.jwt.mongodb.models.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);
  
  Optional<User> findByEmail(String email);
  
  Optional<User> findById(String id);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
