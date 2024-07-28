package com.Desktop.Application.Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.categorymaster;
import com.Desktop.Application.Service.CategoryMasterServiceIterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorymaster")
public class CategoryMasterController {

    @Autowired
    private CategoryMasterServiceIterface service;
    
   
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/addcategory")
    public ResponseEntity<categorymaster> addCategory(
            @RequestPart("categorymaster") String categoryJson,
            @RequestPart("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        // Log received JSON for debugging
        System.out.println("Received JSON: " + categoryJson);

        categorymaster category = mapper.readValue(categoryJson, categorymaster.class);
        categorymaster savedCategory = service.save(category, file);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping("/getAll")
    public List<categorymaster> getAllCategories() {
        return service.findAll();
    }
    
    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    
    
    
    
    @GetMapping("/{categoryid}")
    public ResponseEntity<categorymaster> getCategoryById(@PathVariable int categoryid) {
        return ResponseEntity.ok(service.findById(categoryid));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        try {
            service.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting category");
        }
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<categorymaster> updateCategory(
            @PathVariable("id") int id,
            @RequestPart("categorymaster") String categoryJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            categorymaster category = mapper.readValue(categoryJson, categorymaster.class);
            category.setCategoryid(id);
            categorymaster updatedCategory = service.update(category, file);
            return ResponseEntity.ok(updatedCategory);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
