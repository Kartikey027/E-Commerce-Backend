package com.kartikey.ecommerce.service;

import com.kartikey.ecommerce.dto.OrderResponseDTO;
import com.kartikey.ecommerce.exception.ResourceNotFoundException;
import com.kartikey.ecommerce.mapper.Mappers;
import com.kartikey.ecommerce.model.*;
import com.kartikey.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;

    public OrderResponseDTO placeOrder(Long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not Found"));

        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is Empty");
        }


        Order order = new Order();
        order.setUser(user);
        order.setStatus("PLACED");
        order.setItems(new ArrayList<>());
        order = orderRepo.save(order); // Save before assigning to OrderItem

        double total = 0;

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            int qty = cartItem.getQuantity();
            double price = product.getPrice();

            if (product.getQuantity() < qty) {
                throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
            }


            product.setQuantity(product.getQuantity() - qty);
            productRepo.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(qty);
            orderItem.setPriceAtPurchase(price);
            orderItem.setOrder(order); // Now safe to set

            total += price * qty;

            order.getItems().add(orderItemRepo.save(orderItem));
        }

        order.setTotalAmount(total);
        order = orderRepo.save(order);

        cart.getItems().clear();
        cartRepo.save(cart);

        return Mappers.toOrderDto(order);
    }

    public List<OrderResponseDTO> getOrdersByUser(Long userId){
        List<Order> orders=orderRepo.findByUserId(userId);
        return orders.stream()
                .map(Mappers::toOrderDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
