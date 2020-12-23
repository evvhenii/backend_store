package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
	
	private void validateUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("Object user is null");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new ValidationException("EMAIL is empty");
        }
    }
	
	@Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
	
	@Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
	
	@Override
    public Optional<User> findById(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
    	Optional<User> user = userRepository.findByEmail(email);
        return user;
    }
    
    

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    public User saveUser(User user) throws ValidationException {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	User savedUser = userRepository.save(user);
        return savedUser;
    }
    
    @Override
	public User findByEmailAndPassword(String email, String password) throws ValidationException {
		Optional<User> optUser = findByEmail(email);
		if(!optUser.isPresent()) return null;
		
		User user = optUser.get();
		
		validateUser(user);

		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		}

		return null;
	}
}
