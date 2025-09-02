package com.miApp.AppS.service;

import com.miApp.AppS.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO findAllCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryById(Long categoryId);
    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
    boolean deleteCategory(Long categoryId);

}
