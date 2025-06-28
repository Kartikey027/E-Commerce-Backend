package com.kartikey.ecommerce.service;

import com.kartikey.ecommerce.dto.UserRequestDTO;
import com.kartikey.ecommerce.dto.UserResponseDTO;
import com.kartikey.ecommerce.exception.ResourceNotFoundException;
import com.kartikey.ecommerce.mapper.Mappers;
import com.kartikey.ecommerce.model.User;
import com.kartikey.ecommerce.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserResponseDTO registerUser(UserRequestDTO dto){
        if(userRepo.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email already registered.");
        }
        User user= Mappers.toUser(dto);
        User saved=userRepo.save(user);

        return Mappers.toUserDto(saved);
    }

    public UserResponseDTO getUserByEmail(String email){
        User user=userRepo.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with E-mail "+ email));

        return Mappers.toUserDto(user);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return Mappers.toUserDto(user);
    }
}
