package com.Desktop.Application.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.categorymaster;
import com.Desktop.Application.Repository.CategoryRepository;

@Service
public class CategoryMasterServiceImple {

    @Autowired
    private CategoryRepository categoryRepository;

    private final String uploadDir = "C:/Users/SUBHASH/Downloads/Application2/Application/images/"; // Change to your actual path

    public categorymaster saveCategory(categorymaster category, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(uploadDir + File.separator + fileName));
            
            category.setCategory_image(fileName);
            category.setCreatedon(LocalDate.now());
        }
        return categoryRepository.save(category);
    }

    public List<categorymaster> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<categorymaster> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public categorymaster updateCategory(int id, categorymaster categoryDetails, MultipartFile file) throws IOException {
        Optional<categorymaster> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            categorymaster categoryToUpdate = category.get();
            String oldImageName = categoryToUpdate.getCategory_image();

            categoryToUpdate.setCategoryname(categoryDetails.getCategoryname());
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Files.copy(file.getInputStream(), Paths.get(uploadDir + File.separator + fileName));
                categoryToUpdate.setCategory_image(fileName);
                categoryToUpdate.setCreatedon(LocalDate.now());

                // Delete old image file
                if (oldImageName != null && !oldImageName.isEmpty()) {
                    Files.deleteIfExists(Paths.get(uploadDir + File.separator + oldImageName));
                }
            }
            return categoryRepository.save(categoryToUpdate);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }
    }

    public void deleteCategory(int id) {
        Optional<categorymaster> category = categoryRepository.findById(id);
        System.out.println("In delete service");
        System.out.println("id is "+id);
        if (category.isPresent()) {
            categorymaster categoryToDelete = category.get();
            String imageName = categoryToDelete.getCategory_image();
            
            // Delete image file
            if (imageName != null && !imageName.isEmpty()) {
                try {
                    Files.deleteIfExists(Paths.get(uploadDir + File.separator + imageName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }
    }
}

