package com.example.photos.clone.web;

import com.example.photos.clone.models.PhotoModel;
import com.example.photos.clone.services.PhotosService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class PhotosController {

    //or @AutoWierd for dependency injection
    private final PhotosService photosService;

    public PhotosController(/* or @AutoWierd */PhotosService photosService) {
        this.photosService = photosService; //dependency injection
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/photos")
    public Iterable<PhotoModel> getAllPhotos() {
        return photosService.getPhotos();
    }

    @GetMapping("/photos/{id}")
    public PhotoModel getPhoto(@PathVariable Integer id) { // there is id in {path}
        PhotoModel photoModel = photosService.getPhoto(id);
        if (photoModel == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photoModel;
    }

    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable Integer id) { // there is id in {path}
        photosService.removePhoto(id);
    }

    @PostMapping("/photo")
    public PhotoModel createPhoto(@RequestPart("data") MultipartFile file) throws IOException { // get all body and convert it to json
        return photosService.createPhoto(file);
    }
}
