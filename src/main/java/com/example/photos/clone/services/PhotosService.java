package com.example.photos.clone.services;

import com.example.photos.clone.models.PhotoModel;
import com.example.photos.clone.repositories.PhotosRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// or @Component
@Service
public class PhotosService { // there will be only one instance of this class
    private final PhotosRepository photosRepository;

    public PhotosService(PhotosRepository photosRepository) {
        this.photosRepository = photosRepository;
    }


    public Iterable<PhotoModel> getPhotos() {
        return photosRepository.findAll();
    }

    public PhotoModel getPhoto(Integer id) {
        return photosRepository.findById(id).orElse(null);
    }

    public void removePhoto(Integer id) {
        photosRepository.deleteById(id);
    }

    public PhotoModel createPhoto(MultipartFile file) throws IOException {
        PhotoModel photoModel = new PhotoModel();
        // photoModel.setId(UUID.randomUUID().toString()); //generates pseudo random id
        photoModel.setFileName(file.getOriginalFilename()); // do not use getFileName
        photoModel.setData(file.getBytes());
        photoModel.setContentType(file.getContentType());
        photosRepository.save(photoModel);
        return photoModel;
    }
}
