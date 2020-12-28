package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ConfiguredModelMapper;
import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegistrationProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthRegistrationController {
    private UserService userService;
    private JwtProvider jwtProvider;
    private final ConfiguredModelMapper modelMapper = new ConfiguredModelMapper();

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationProfile registrationProfile) throws ValidationException {
    	System.out.println(registrationProfile);
    	User user = modelMapper.map(registrationProfile, User.class);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) throws ValidationException {
        User user = userService.findByEmailAndPassword(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUserId());
        return new AuthResponse(token);
    }
}