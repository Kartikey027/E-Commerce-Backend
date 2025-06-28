package com.kartikey.ecommerce.controller;

import com.kartikey.ecommerce.dto.ProductRequestDTO;
import com.kartikey.ecommerce.dto.ProductResponseDTO;
import com.kartikey.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseDTO create(@Valid @RequestBody ProductRequestDTO dto){
        return productService.createProduct(dto);
    }

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/category/{id}")
    public List<ProductResponseDTO> getByCategory(@PathVariable Long id){
        return productService.getProductsByCategory(id);
    }


}
