package com.Desktop.Application.Controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.categorymaster;
import com.Desktop.Application.Service.CategoryMasterServiceImple;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/categorymaster")
public class CategoryMasterController {

    @Autowired
    private CategoryMasterServiceImple categoryService;

    @PostMapping("/addcategory")
    public ResponseEntity<categorymaster> createCategory(
            @RequestPart("category") String categoryJson,
            @RequestPart("file") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        categorymaster category = objectMapper.readValue(categoryJson, categorymaster.class);
        categorymaster savedCategory = categoryService.saveCategory(category, file);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping("/getAll")
    public List<categorymaster> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<categorymaster> getCategoryById(@PathVariable int id) {
        Optional<categorymaster> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<categorymaster> updateCategory(
            @PathVariable int id,
            @RequestPart("category") String categoryJson,
            @RequestPart("file") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        categorymaster categoryDetails = objectMapper.readValue(categoryJson, categorymaster.class);
        categorymaster updatedCategory = categoryService.updateCategory(id, categoryDetails, file);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
