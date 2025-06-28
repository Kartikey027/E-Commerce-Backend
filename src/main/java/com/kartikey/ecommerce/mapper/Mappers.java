package com.kartikey.ecommerce.mapper;

import com.kartikey.ecommerce.dto.*;
import com.kartikey.ecommerce.dto.*;
import com.kartikey.ecommerce.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class Mappers {


    public static User toUser(UserRequestDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }

    public static UserResponseDTO toUserDto(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }



    public static Category toCategory(CategoryRequestDTO dto, Category parent) {
        return Category.builder()
                .name(dto.getName())
                .parent(parent)
                .build();
    }

    public static CategoryResponseDTO toCategoryDto(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .parentName(category.getParent() != null ? category.getParent().getName() : null)
                .build();
    }


    public static Product toProduct(ProductRequestDTO dto, Category category) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .imageURL(dto.getImageURL())
                .category(category)
                .build();
    }

    public static ProductResponseDTO toProductDto(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageURL())
                .categoryName(product.getCategory().getName())
                .build();
    }



    public static CartItemResponseDTO toCartItemDto(CartItem item) {
        double total = item.getQuantity() * item.getProduct().getPrice();
        return CartItemResponseDTO.builder()
                .id(item.getId())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .quantity(item.getQuantity())
                .totalPrice(total)
                .build();
    }

    public static CartResponseDTO toCartDto(Cart cart) {
        List<CartItemResponseDTO> items = cart.getItems().stream()
                .map(Mappers::toCartItemDto)
                .collect(Collectors.toList());

        double totalAmount = items.stream()
                .mapToDouble(CartItemResponseDTO::getTotalPrice)
                .sum();

        return CartResponseDTO.builder()
                .id(cart.getId())
                .items(items)
                .amount(totalAmount)
                .build();
    }



    public static OrderItemResponseDTO toOrderItemDto(OrderItem item) {
        double subtotal = item.getPriceAtPurchase() * item.getQuantity();
        return OrderItemResponseDTO.builder()
                .id(item.getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .priceAtPurchase(item.getPriceAtPurchase())
                .subTotal(subtotal)
                .build();
    }

    public static OrderResponseDTO toOrderDto(Order order) {
        List<OrderItemResponseDTO> items = order.getItems().stream()
                .map(Mappers::toOrderItemDto)
                .collect(Collectors.toList());

        return OrderResponseDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .items(items)
                .build();
    }
}
