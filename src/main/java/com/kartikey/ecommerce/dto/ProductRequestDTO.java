package com.kartikey.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    @NotBlank
    @Size(min=3,max = 50)
    private String name;

    @NotNull
    @PositiveOrZero
    private Double price;

    private String description;

    @NotNull
    @Min(0)
    private Integer quantity;

    private String imageURL;

    @NotNull
    private Long categoryId;
}
