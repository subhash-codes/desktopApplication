package com.Desktop.Application.Service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public interface CloudinaryImageService {

    public Map upload(MultipartFile multipartFile);

}
