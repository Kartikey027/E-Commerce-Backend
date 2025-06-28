package com.kartikey.ecommerce.service;

import com.kartikey.ecommerce.dto.CartResponseDTO;
import com.kartikey.ecommerce.exception.ResourceNotFoundException;
import com.kartikey.ecommerce.mapper.Mappers;
import com.kartikey.ecommerce.model.Cart;
import com.kartikey.ecommerce.model.CartItem;
import com.kartikey.ecommerce.model.Product;
import com.kartikey.ecommerce.model.User;
import com.kartikey.ecommerce.repository.CartItemRepo;
import com.kartikey.ecommerce.repository.CartRepo;
import com.kartikey.ecommerce.repository.ProductRepo;
import com.kartikey.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private ProductRepo productRepo;

    public CartResponseDTO addToCart(Long userId,Long productId,int quantity){
        User user=userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found"));
        Product product=productRepo.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not Found"));

        Cart cart=cartRepo.findByUserId(userId).orElseGet(()-> {
            Cart newCart= new Cart();
            newCart.setUser(user);
            return cartRepo.save(newCart);
        });

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepo.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            cart.getItems().add(cartItemRepo.save(newItem));
        }

        return Mappers.toCartDto(cartRepo.save(cart));
    }

    public CartResponseDTO getCartByUserId(Long userId){
        Cart cart=cartRepo.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found"));
        return Mappers.toCartDto(cart);
    }

    public CartResponseDTO updateItemQuantity(Long cartItemId,int quantity){
        CartItem item= cartItemRepo.findById(cartItemId)
                .orElseThrow(()-> new ResourceNotFoundException("Item not Found"));
        item.setQuantity(quantity);
        cartItemRepo.save(item);
        return Mappers.toCartDto(item.getCart());
    }

    public void removeItem(Long cartItemId){
        CartItem item= cartItemRepo.findById(cartItemId)
                .orElseThrow(()-> new ResourceNotFoundException("Item not Found"));
        cartItemRepo.delete(item);
    }

    public CartResponseDTO clearCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cart.getItems().clear();
        return Mappers.toCartDto(cartRepo.save(cart));
    }
}
