package com.Desktop.Application.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.categorymaster;

public interface CategoryMasterServiceIterface {
    categorymaster save(categorymaster category, MultipartFile file) throws IOException;
    List<categorymaster> findAll();
    categorymaster findById(int categoryId);
    categorymaster update(categorymaster category, MultipartFile file) throws IOException;
    void deleteById(int id) throws IOException;
}
