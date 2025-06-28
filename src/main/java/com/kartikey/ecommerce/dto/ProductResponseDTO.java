package com.kartikey.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private  String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String imageUrl;
    private String categoryName;
    private LocalDateTime createdAt;
}
