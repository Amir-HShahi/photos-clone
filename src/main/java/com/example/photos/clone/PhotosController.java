package com.example.photos.clone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosController {

    @GetMapping("/hello")
    public String hello() {
        return  "hello world";
    }
}
