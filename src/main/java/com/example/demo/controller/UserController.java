package com.example.demo.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ConfiguredModelMapper;
import com.example.demo.dto.MyProfile;
import com.example.demo.dto.UserProfile;
import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
//@RequestMapping("/users")
@AllArgsConstructor
@Log
public class UserController {
	private final UserService userService;
	private final ConfiguredModelMapper modelMapper = new ConfiguredModelMapper();
	
	@GetMapping("/profile")
	public ResponseEntity<MyProfile> find(Principal principal) {
		log.info("Handling get user information request: ");
		int id = Integer.parseInt(principal.getName());
		Optional<User> optUser = userService.findById(id);
		if(optUser.isEmpty()) return ResponseEntity.notFound().build();
		
		MyProfile myProfile = modelMapper.map(optUser.get(), MyProfile.class);
		return ResponseEntity.ok().body(myProfile);	
    }	
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<UserProfile> findById(@PathVariable int id) {		
		log.info("Handling get user information request: " + id);
		Optional<User> optUser = userService.findById(id);
		if(optUser.isEmpty()) return ResponseEntity.notFound().build();
				
		UserProfile userProfile = modelMapper.map(optUser.get(), UserProfile.class);
		return ResponseEntity.ok().body(userProfile);	
	}
	
	@PutMapping("/update_user")
    public ResponseEntity<User> updateUser(@RequestBody MyProfile myProfile, Principal principal) throws ValidationException {
        log.info("Handling updating user");
        int id = Integer.parseInt(principal.getName());
		User user = userService.findById(id).get();
		
        User newUser = modelMapper.map(myProfile, User.class);
        newUser.setUserId(user.getUserId());
        newUser.setPassword(user.getPassword());
        
        userService.updateUser(newUser);
        return ResponseEntity.ok().build();
    }
}
