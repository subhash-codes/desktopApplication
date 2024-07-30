package com.Desktop.Application.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.productmaster;
import com.Desktop.Application.Service.ProductMasterServiceImple;
import com.Desktop.Application.Service.ProductMasterServiceIterface;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productmaster")
public class ProductMasterController {
	
    @Autowired
	private ProductMasterServiceImple service;
	
	  @PostMapping("/addProduct")
	    ResponseEntity<productmaster> saveProduct(@RequestPart("productmaster") productmaster product,
	    		                            @PathVariable String categoryname,
	                                        @RequestParam("file") MultipartFile file)  {
	                                     
	        try {
	            // Deserialize JSON string to Product object
//	            ObjectMapper objectMapper = new ObjectMapper();
//	            Product product = objectMapper.readValue(productString, Product.class);
	            productmaster savedProduct = service.save(product, categoryname, file);
	            return ResponseEntity.ok(savedProduct);
       
	        }catch (IOException e){

	            return ResponseEntity.status(500).body(null);
	        }
	        
	    }
	    
}
