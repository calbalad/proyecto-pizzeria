package com.pizzaiolo.spring.jwt.mongodb.security.services;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pizzaiolo.spring.jwt.mongodb.models.Photo;
import com.pizzaiolo.spring.jwt.mongodb.repository.PhotoRepository;

@Service
public class PhotoServiceImpl {

    @Autowired
    private PhotoRepository  photoRepo;

    public String addPhoto(String title, MultipartFile file) throws IOException { 
        Photo photo = new Photo(title); 
        photo.setImage(
          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        photo = photoRepo.insert(photo); return photo.getId(); 
    }

    public Photo getPhoto(String id) { 
        return photoRepo.findById(id).get(); 
    }
}