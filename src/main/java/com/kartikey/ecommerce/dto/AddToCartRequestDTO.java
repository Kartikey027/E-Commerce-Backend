package com.kartikey.ecommerce.dto;

import lombok.Data;

@Data
public class AddToCartRequestDTO {
    private Long productId;
    private int quantity;
}
