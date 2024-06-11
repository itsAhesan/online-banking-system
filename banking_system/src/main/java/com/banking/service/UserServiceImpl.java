package com.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.banking.model.User;
import com.banking.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {	
    	
    //	userRepository.findByEmail(user.getEmail()).orElse(null);
        if (userRepository.findByEmail(user.getEmail()).orElse(null) != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

	

}
