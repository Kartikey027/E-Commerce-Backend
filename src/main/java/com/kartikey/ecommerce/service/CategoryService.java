package com.kartikey.ecommerce.service;

import com.kartikey.ecommerce.dto.CategoryRequestDTO;
import com.kartikey.ecommerce.dto.CategoryResponseDTO;
import com.kartikey.ecommerce.exception.ResourceNotFoundException;
import com.kartikey.ecommerce.mapper.Mappers;
import com.kartikey.ecommerce.model.Category;
import com.kartikey.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto){
        Category parent=null;
        if(dto.getParentId()!=null){
            parent= categoryRepo.findById(dto.getParentId())
                    .orElseThrow(()->new ResourceNotFoundException("Parent category not found"));
        }
        Category category= Mappers.toCategory(dto, parent);
        return Mappers.toCategoryDto(categoryRepo.save(category));
    }

    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(Mappers::toCategoryDto)
                .toList();
    }

    public CategoryResponseDTO getCategoryById(Long id) {
        Category cat = categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return Mappers.toCategoryDto(cat);
    }
}
