package com.pizzaiolo.authorization.repositories;

import com.pizzaiolo.authorization.models.entities.Address;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, ObjectId> {
    Optional<Address> findById(String id);
}
