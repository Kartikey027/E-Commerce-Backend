package com.kartikey.ecommerce.dto;

import com.kartikey.ecommerce.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDTO {
    private Long id;
    private List<CartItemResponseDTO> items;
    private Double amount;
}
