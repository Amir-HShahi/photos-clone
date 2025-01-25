package com.example.photos.clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
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
    public PhotoModel createPhoto(@RequestPart("data") MultipartFile file) throws IOException { // get all body and convert it to json
        PhotoModel photoModel = new PhotoModel();
        photoModel.setId(UUID.randomUUID().toString()); //generates pseudo random id
        photoModel.setFileName(file.getName());
        photoModel.setData(file.getBytes());
        db.put(photoModel.getId(), photoModel);
        return photoModel;
    }
}
