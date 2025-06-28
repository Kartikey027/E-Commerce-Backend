package com.kartikey.ecommerce.controller;

import com.kartikey.ecommerce.dto.UserRequestDTO;
import com.kartikey.ecommerce.dto.UserResponseDTO;
import com.kartikey.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO dto){
        return userService.registerUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
