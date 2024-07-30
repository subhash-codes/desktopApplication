package com.Desktop.Application.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Service.CloudinaryImageService;

import java.util.Map;

@RestController
@RequestMapping("/cloudinary/upload")
public class CloudinaryImageUploadController {

    @Autowired
    private CloudinaryImageService cloudinaryImageService;
    
    @PostMapping
    ResponseEntity<Map> uploadImage(@RequestParam("category_image")MultipartFile file){

        Map data = cloudinaryImageService.upload(file);

        return new ResponseEntity<>(data , HttpStatus.OK);

    }
}