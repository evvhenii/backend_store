package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;

public interface UserService {
	
	User saveUser(User user) throws ValidationException;

    void deleteUser(Integer userId);

    Optional<User> findByEmail(String email);

    List<User> findAll();

	User findByEmailAndPassword(String email, String password) throws ValidationException;

	Optional<User> findById(Integer userId);

	void updateUser(User user);
}
