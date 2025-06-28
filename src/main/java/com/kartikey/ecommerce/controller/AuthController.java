package com.kartikey.ecommerce.controller;

import com.kartikey.ecommerce.dto.AuthRequestDTO;
import com.kartikey.ecommerce.dto.AuthResponseDTO;
import com.kartikey.ecommerce.dto.UserRequestDTO;
import com.kartikey.ecommerce.mapper.Mappers;
import com.kartikey.ecommerce.model.User;
import com.kartikey.ecommerce.repository.UserRepo;
import com.kartikey.ecommerce.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserRequestDTO dto){
        User user= Mappers.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO dto){
        UsernamePasswordAuthenticationToken authInput = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
         authenticationManager.authenticate(authInput);
         String token=jwtUtil.generateToken(dto.getEmail());
         return new AuthResponseDTO(token);
    }
}
