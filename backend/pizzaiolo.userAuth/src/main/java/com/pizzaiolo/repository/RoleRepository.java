package com.pizzaiolo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pizzaiolo.models.Role;
import com.pizzaiolo.models.RoleType;

public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(RoleType name);
	}
