// src/main/java/com/microedge/services/CategoryService.java
package com.microedge.services;

import com.microedge.dto.CategoryDto;
import com.microedge.exceptions.ResourceNotFoundException;
import com.microedge.models.Category;
import com.microedge.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CategoryDto findById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
        return toDto(category);
    }

    public CategoryDto create(CategoryDto dto) {
        Category category = fromDto(dto);
        Category saved = categoryRepository.save(category);
        return toDto(saved);
    }

    public void deleteById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    private Category fromDto(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}