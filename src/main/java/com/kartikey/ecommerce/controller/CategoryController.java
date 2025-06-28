package com.kartikey.ecommerce.controller;

import com.kartikey.ecommerce.dto.CategoryRequestDTO;
import com.kartikey.ecommerce.dto.CategoryResponseDTO;
import com.kartikey.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO dto){
        return categoryService.createCategory(dto);
    }

    @GetMapping
    public List<CategoryResponseDTO> listAll(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
}
