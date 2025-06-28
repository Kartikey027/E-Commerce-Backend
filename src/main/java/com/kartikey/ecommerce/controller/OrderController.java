package com.kartikey.ecommerce.controller;

import com.kartikey.ecommerce.dto.OrderResponseDTO;
import com.kartikey.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}")
    public OrderResponseDTO placeOrder(@PathVariable Long userId){
        return orderService.placeOrder(userId);
    }

    @GetMapping("/{userId}")
    public List<OrderResponseDTO> viewOrder(@PathVariable Long userId){
        return orderService.getOrdersByUser(userId);
    }
}
