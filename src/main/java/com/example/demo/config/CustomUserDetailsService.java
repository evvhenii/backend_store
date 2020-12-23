package com.example.demo.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    	System.out.println("Im in loadUserByUsername id = " + id);
        User user = userService.findById(Integer.parseInt(id)).get();
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
