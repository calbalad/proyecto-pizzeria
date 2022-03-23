package com.pizzaiolo.authorization.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pizzaiolo.authorization.models.entities.RefreshToken;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    RefreshToken findByValue(String value);
}