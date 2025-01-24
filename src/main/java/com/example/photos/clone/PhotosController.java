package com.example.photos.clone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class PhotosController {

    private HashMap<String, PhotoModel> db = new HashMap<>() {
        {
            put("1", new PhotoModel("1", "hey.png"));
        }
    };

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/photos")
    public Collection<PhotoModel> getAllPhotos() {
        return db.values();
    }

    @GetMapping("/photos/{id}")
    public PhotoModel getPhoto(@PathVariable String id) { // there is id in {path}
        PhotoModel photoModel = db.get(id);
        if (photoModel == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photoModel;
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable String id) { // there is id in {path}
        PhotoModel photoModel = db.remove(id);
        if (photoModel == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photo")
    public PhotoModel createPhoto(@RequestBody @Valid PhotoModel photoModel) { // get all body and convert it to json
        photoModel.setId(UUID.randomUUID().toString()); //generates pseudo random id
        db.put(photoModel.getId(), photoModel);
        return photoModel;
    }
}
