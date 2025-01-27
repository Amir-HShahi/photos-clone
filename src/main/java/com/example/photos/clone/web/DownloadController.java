package com.example.photos.clone.web;

import com.example.photos.clone.models.PhotoModel;
import com.example.photos.clone.services.PhotosService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotosService photosService;

    public DownloadController(PhotosService photosService) {
        this.photosService = photosService; // dependency injection
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadPhoto(@PathVariable Integer id) {
        PhotoModel photoModel = photosService.getPhoto(id);
        if (photoModel == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photoModel.getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photoModel.getContentType())); // set content type
        /* 'attachment' for download or 'inline' for display*/
        ContentDisposition contentDisposition = ContentDisposition
                .builder("attachment")
                .filename(photoModel.getFileName())
                .build();
        headers.setContentDisposition(contentDisposition);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
