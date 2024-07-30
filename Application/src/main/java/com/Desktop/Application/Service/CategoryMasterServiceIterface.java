package com.Desktop.Application.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.categorymaster;

public interface CategoryMasterServiceIterface {
	
	//public categorymaster addCategory(String categoryname,String category_image);

    categorymaster save(categorymaster product , MultipartFile file) throws IOException ;
    
    List<categorymaster> findAll();

    categorymaster findById(int categoryId);

    categorymaster update(categorymaster product,MultipartFile file);

    void deleteById(int id);
   
}
