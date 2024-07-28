package com.Desktop.Application.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Desktop.Application.Entity.categorymaster;
import com.Desktop.Application.Repository.CategoryRepository;

@Service
public class CategoryMasterServiceImple implements CategoryMasterServiceIterface {

    @Autowired
    private CategoryRepository repo;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public categorymaster save(categorymaster category, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());  // Ensure the directory exists
            Files.copy(file.getInputStream(), filePath);
            
            // Normalize path
            String normalizedFileName = fileName.replace("\\", "/");
            category.setCategory_image(normalizedFileName);  // Save only the filename
            
            // Debugging
            System.out.println("Image saved at: " + filePath.toString());
        }
        category.setCreatedon(LocalDate.now());
        return repo.save(category);
    }

    @Override
    public List<categorymaster> findAll() {
        return repo.findAll();
    }

    @Override
    public categorymaster findById(int categoryId) {
        Optional<categorymaster> result = repo.findById(categoryId);
        return result.orElseThrow(() -> new RuntimeException("Did not find Category Id - " + categoryId));
    }

    @Override
    public categorymaster update(categorymaster category, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());  // Ensure the directory exists
            Files.copy(file.getInputStream(), filePath);
            
            // Normalize path
            String normalizedFileName = fileName.replace("\\", "/");
            category.setCategory_image(normalizedFileName);  // Save only the filename
            
            // Debugging
            System.out.println("Image updated at: " + filePath.toString());
        }
        category.setCreatedon(LocalDate.now());
        return repo.save(category);
    }
    @Override
    public void deleteById(int id) throws IOException {
        repo.deleteById(id);
    }
}
