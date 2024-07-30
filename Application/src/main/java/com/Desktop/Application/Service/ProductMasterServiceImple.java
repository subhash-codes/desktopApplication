package com.Desktop.Application.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.categorymaster;
import com.Desktop.Application.Entity.productmaster;
import com.Desktop.Application.Repository.CategoryRepository;
import com.Desktop.Application.Repository.ProductRepository;


@Service
public class ProductMasterServiceImple implements ProductMasterServiceIterface {
	
	@Autowired
    private ProductRepository repo;
	@Autowired
    private CloudinaryImageServiceImpl cloudinaryImageService;
	
	@Autowired
	private CategoryRepository crepo;
	
	
//   	@Override
//	public productmaster save(productmaster product,String categoryname, MultipartFile file) throws IOException {
//
//
//		 	    categorymaster category = crepo.findCategoryNameByCategoryName(categoryname);
//		         product.setCategory(category);
//	             product.setCreatedon(LocalDate.now());
//		         Map<String, Object> uploadResult =cloudinaryImageService.upload(file);
//		         String imageUrl = (String) uploadResult.get("url");
//		        
//                product.setCreatedon(LocalDate.now());
//		        product.setProduct_image(imageUrl);
//               
//		        return repo.save(product);
//		    
//	}

	@Override
	public List<productmaster> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public productmaster findById(int productid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public productmaster update(productmaster product, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public productmaster save(productmaster product, String categoryname, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
