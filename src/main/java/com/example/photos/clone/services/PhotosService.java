package com.example.photos.clone.services;

import com.example.photos.clone.models.PhotoModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

// or @Component
@Service
public class PhotosService { // there will be only one instance of this class
    private final HashMap<String, PhotoModel> db = new HashMap<>() {
        {
            put("1", new PhotoModel("1", "hey.png"));
        }
    };

    public Collection<PhotoModel> getPhotos() {
        return db.values();
    }

    public PhotoModel getPhoto(String id) {
        return db.get(id);
    }

    public PhotoModel removePhoto(String id) {
        return db.remove(id);
    }

    public PhotoModel createPhoto(MultipartFile file) throws IOException {
        PhotoModel photoModel = new PhotoModel();
        photoModel.setId(UUID.randomUUID().toString()); //generates pseudo random id
        photoModel.setFileName(file.getOriginalFilename()); // do not use getFileName
        photoModel.setData(file.getBytes());
        db.put(photoModel.getId(), photoModel);
        return photoModel;
    }
}
