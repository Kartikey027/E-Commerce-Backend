package com.kartikey.ecommerce.controller;

import com.kartikey.ecommerce.dto.AddToCartRequestDTO;
import com.kartikey.ecommerce.dto.CartResponseDTO;
import com.kartikey.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/add")
    public CartResponseDTO create(@PathVariable Long userId, @Valid @RequestBody AddToCartRequestDTO dto){
        return cartService.addToCart(userId,dto.getProductId(),dto.getQuantity());
    }

    @GetMapping("/{userId}")
    public CartResponseDTO getAll(@PathVariable Long userId){
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{userId}/remove/{itemId}")
    public CartResponseDTO removeItem(@PathVariable Long userId,
                                      @PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return cartService.getCartByUserId(userId);
    }

    @PutMapping("/{userId}/item/{itemId}")
    public CartResponseDTO updateQuantity(@PathVariable Long userId,
                                          @PathVariable Long itemId,
                                          @RequestParam int quantity) {
        return cartService.updateItemQuantity(itemId, quantity);
    }

    @DeleteMapping("/{userId}/clear")
    public CartResponseDTO clearCart(@PathVariable Long userId) {
        return cartService.clearCart(userId);
    }
}
