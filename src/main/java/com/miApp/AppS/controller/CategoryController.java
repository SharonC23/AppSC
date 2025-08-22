package com.miApp.AppS.controller;

import com.miApp.AppS.dto.CategoryDTO;
import com.miApp.AppS.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/Categories")

public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoriService, CategoryService categoryServiceService) {
        this.categoryService = categoryServiceService;

    }

    @GetMapping ("/all")
    public ResponseEntity <List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping ("/{categoryId}")
    public ResponseEntity <CategoryDTO> getCategoryById(Long categoryId) {
        CategoryDTO category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/create")
    public ResponseEntity <CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
         CategoryDTO Category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(Category);
    }
}
