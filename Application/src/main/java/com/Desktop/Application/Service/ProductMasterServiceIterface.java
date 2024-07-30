package com.Desktop.Application.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.productmaster;

public interface ProductMasterServiceIterface {
	
productmaster save(productmaster product ,String categoryname, MultipartFile file) throws IOException ;
    
    List<productmaster> findAll();

    productmaster findById(int productid);

    productmaster update(productmaster product,MultipartFile file);

    void deleteById(int id);

}
