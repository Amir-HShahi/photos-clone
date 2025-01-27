package com.example.photos.clone.repositories;

import com.example.photos.clone.models.PhotoModel;
import org.springframework.data.repository.CrudRepository;

public interface PhotosRepository extends CrudRepository<PhotoModel, Integer> {
}
