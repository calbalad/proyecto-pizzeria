package com.pizzaiolo.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pizzaiolo.spring.jwt.mongodb.models.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> { 
	
}