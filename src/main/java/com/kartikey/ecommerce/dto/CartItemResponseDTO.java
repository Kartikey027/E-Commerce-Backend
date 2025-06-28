package com.kartikey.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponseDTO {
        private Long id;
        private String productName;
        private Double price;
        private Integer quantity;
        private Double totalPrice;
}
