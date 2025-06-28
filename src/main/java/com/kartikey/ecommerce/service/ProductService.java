package com.kartikey.ecommerce.service;

import com.kartikey.ecommerce.dto.ProductRequestDTO;
import com.kartikey.ecommerce.dto.ProductResponseDTO;
import com.kartikey.ecommerce.exception.ResourceNotFoundException;
import com.kartikey.ecommerce.mapper.Mappers;
import com.kartikey.ecommerce.model.Category;
import com.kartikey.ecommerce.model.Product;
import com.kartikey.ecommerce.repository.CategoryRepo;
import com.kartikey.ecommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public ProductResponseDTO createProduct(ProductRequestDTO dto){
        Category category= categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product= Mappers.toProduct(dto,category);
        Product saved= productRepo.save(product);

        return Mappers.toProductDto(saved);
    }
    public ProductResponseDTO getProductById(Long id){
        Product product=productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

        return Mappers.toProductDto(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepo.findAll().stream()
                .map(Mappers::toProductDto)
                .collect(Collectors.toList());
    }

    public List<ProductResponseDTO> getProductsByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId).stream()
                .map(Mappers::toProductDto)
                .collect(Collectors.toList());
    }
}
